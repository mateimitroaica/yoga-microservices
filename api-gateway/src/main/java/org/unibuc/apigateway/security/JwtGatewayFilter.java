package org.unibuc.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.List;

@Component
public class JwtGatewayFilter implements GlobalFilter, Ordered {

    private static final String SECRET = "sa4u0SjY2lQv2NGsD61y5Qfe304fURqfbRqkD4lke5w=";
    private final WebClient.Builder webClientBuilder;

    public JwtGatewayFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();
        String path = req.getURI().getPath();
        String method = req.getMethod().name();

        // Public endpoints
        boolean isPublic =
                path.matches(".*/actuator/prometheus$") ||  // Allow Actuator without JWT
                        (method.equals("POST") && (path.equals("/api/auth/login") || path.equals("/api/auth/register"))) ||
                        (method.equals("GET") && (
                                path.startsWith("/api/yoga-classes") ||
                                        path.startsWith("/api/instructors") ||
                                        path.startsWith("/api/studios")));

        if (isPublic) {
            return chain.filter(exchange);
        }

        // Protected endpoints (require token)
        List<String> authHeaders = req.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION);
        if (authHeaders.isEmpty() || !authHeaders.get(0).startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeaders.get(0).substring(7);

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            List<String> roles = claims.get("authorities", List.class);

            // Yoga Class restrictions (POST/PUT/DELETE → ADMIN only)
            if (path.startsWith("/api/yoga-classes") &&
                    (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) &&
                    !roles.contains("ROLE_ADMIN")) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            // Reservation restrictions
            if (path.startsWith("/api/reservations")) {

                if (method.equals("POST") && !roles.contains("ROLE_USER")) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }

                if (method.equals("GET")) {
                    if (path.matches("/api/reservations/class/\\d+") && !roles.contains("ROLE_ADMIN")) {
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().setComplete();
                    }

                    if (!roles.contains("ROLE_USER") && !roles.contains("ROLE_ADMIN")) {
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().setComplete();
                    }
                }
            }

            // All authenticated users can create/view subscriptions
            if (path.startsWith("/api/subscriptions")) {
                return chain.filter(exchange);
            }

            return chain.filter(exchange);

        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private boolean isPublic(String path, String method) {
        return (method.equals("POST") &&
                (path.equals("/api/auth/login") || path.equals("/api/auth/register") || path.equals("/api/auth/logout")))
                || (method.equals("GET") && (
                path.startsWith("/api/yoga-classes") ||
                        path.startsWith("/api/instructors") ||
                        path.startsWith("/api/studios")));
    }

    private boolean isProtectedModification(String path, String method) {
        return path.startsWith("/api/yoga-classes") &&
                (method.equals("POST") || method.equals("PUT") || method.equals("DELETE"));
    }

    private Mono<Boolean> isTokenBlacklisted(String token) {
        return webClientBuilder.build()
                .get()
                .uri("http://auth-service/api/auth/token/blacklisted?token=" + token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorReturn(false); // Fail open if auth-service down
    }

    @Override
    public int getOrder() {
        return -1;
    }
}


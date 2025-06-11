package org.unibuc.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/api/auth/**")
                        .uri("lb://auth-service"))
                .route("yoga-class", r -> r.path("/api/yoga-classes/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("yogaClassCB")
                                .setFallbackUri("forward:/fallback/yoga-classes")))
                        .uri("lb://yoga-class-service"))
                .route("studio", r -> r.path("/api/studios/**")
                        .uri("lb://studio-service"))
                .route("instructor", r -> r.path("/api/instructors/**")
                        .uri("lb://instructor-service"))
                .route("subscription", r -> r.path("/api/subscriptions/**")
                        .uri("lb://subscription-service"))
                .route("reservation", r -> r.path("/api/reservations/**")
                        .uri("lb://reservation-service"))
                .build();
    }
}

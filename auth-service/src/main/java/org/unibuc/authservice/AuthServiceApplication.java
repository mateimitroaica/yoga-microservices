package org.unibuc.authservice;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthServiceApplication.class, args);
//        System.out.println(new BCryptPasswordEncoder().encode("admin123"));
//        byte[] key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();
//        System.out.println(Base64.getEncoder().encodeToString(key));
    }

}

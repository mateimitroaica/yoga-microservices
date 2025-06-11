package org.unibuc.studioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudioServiceApplication.class, args);
    }

}

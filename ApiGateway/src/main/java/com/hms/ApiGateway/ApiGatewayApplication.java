package com.hms.ApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	// Define explicit routes programmatically so Gateway definitely routes requests.
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("users-service", r -> r.path("/UserService/**")
						.uri("lb://USERSERVICE"))
				.route("users-friendly", r -> r.path("/users/**")
						.filters(f -> f.rewritePath("/users/(?<segment>.*)", "/UserService/${segment}"))
						.uri("lb://USERSERVICE"))
				.route("hotels-service", r -> r.path("/HotelService/**")
						.uri("lb://HOTELSERVICE"))
				.route("ratings-service", r -> r.path("/RatingService/**")
						.uri("lb://RATINGSERVICE"))
				.build();
	}

}

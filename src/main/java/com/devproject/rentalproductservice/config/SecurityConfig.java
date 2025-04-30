package com.devproject.rentalproductservice.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.devproject.rentalproductservice.jwt.CustomJwt;
import com.devproject.rentalproductservice.jwt.CustomJwtConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Value("${app.security.enabled:false}")
	private boolean securityEnabled;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Allowed origin
		configuration.setAllowedMethods(List.of("GET", "POST")); // Allowed HTTP methods
		configuration.setAllowedHeaders(List.of("*")); // Allow all headers
		configuration.setAllowCredentials(true); // Optional for credentials

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // Apply to all endpoints
		return source;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		if (securityEnabled) {
			http.cors(Customizer.withDefaults())
					.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).oauth2ResourceServer(
							(oauth2) -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(customJwtConverter())));
		} else {
			http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());

			http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

			// Disable CSRF
			http.csrf(csrf -> csrf.disable());
		}

		return http.build();
	}

	@Bean
	public Converter<Jwt, CustomJwt> customJwtConverter() {
		return new CustomJwtConverter();
	}
}

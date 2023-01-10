package com.semester3.davines.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeRequests(auth -> auth
                                .antMatchers("/products",
                                        "/products/details/**", "products/delete/*",
                                        "/products/type/**", "/products/update/*", "/products/create",
                                        "/login", "/users", "/users/details/**", "/users/update/*",
                                        "/users/delete/*", "/users/create",
                                        "/series", "/series/update/*", "/series/delete/*",
                                        "/series/details/**", "/series/create",
                                        "/notifications", "/orders/*", "/orders/user/*",
                                        "/orders/create").permitAll()
//                        .antMatchers().hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .headers(headers -> headers
                        .cacheControl().disable()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}

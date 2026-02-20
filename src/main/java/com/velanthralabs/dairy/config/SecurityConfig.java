package com.velanthralabs.dairy.config;

import com.velanthralabs.dairy.core.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configure(http))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                        // 1. Only SUPER_ADMIN can manage users
                        .requestMatchers("/api/users/**").hasRole("SUPER_ADMIN")

                        // 2. Only SUPER_ADMIN and EDITOR can create/edit records (POST, PUT, DELETE)
                        .requestMatchers(HttpMethod.POST, "/api/dairy/**").hasAnyRole("SUPER_ADMIN", "EDITOR")
                        .requestMatchers(HttpMethod.PUT, "/api/dairy/**").hasAnyRole("SUPER_ADMIN", "EDITOR")

                        // 3. EVERYONE (including VIEWER) can see the data (GET)
                        .requestMatchers(HttpMethod.GET, "/api/dairy/**").hasAnyRole("SUPER_ADMIN", "EDITOR", "VIEWER")

                        // 4. Only super_admin can add/delete/update vendors
                        .requestMatchers("/api/vendors/**").hasRole("SUPER_ADMIN")

                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
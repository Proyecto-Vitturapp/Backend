package com.vittur.vitturapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final ApiKeyAuthenticationFilter apiKeyAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        try {
            http.csrf(csrf -> csrf.disable());

            http.authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/api/usuarios", "/api/usuarios/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/usuarios/total").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/vehiculos/total").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/vehiculos/usuarioid/**").hasAnyRole("CLIENTE", "MECANICO")
                    .requestMatchers(HttpMethod.GET, "/api/revisiones/total").permitAll()
                    .requestMatchers("/api/revisiones", "/api/revisiones/**").hasRole("MECANICO")
                    .requestMatchers("/api/vehicles", "/api/vehicles/**").hasAnyRole("CLIENTE", "MECANICO")
                    .anyRequest().authenticated()
            );

            http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            http.addFilterBefore(apiKeyAuthFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

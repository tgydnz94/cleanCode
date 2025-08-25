package com.bootcamp.cleanCode.business.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("api/brands/**").permitAll()
                .requestMatchers("api/cars/**").permitAll()
                .requestMatchers("api/companies/**").permitAll()
                .requestMatchers("api/companyinvoices/**").permitAll()
                .requestMatchers("api/customerinvoices/**").permitAll()
                .requestMatchers("api/customers/**").permitAll()
                .requestMatchers("api/fuels/**").permitAll()
                .requestMatchers("api/locations/**").permitAll()
                .requestMatchers("api/maintenances/**").permitAll()
                .requestMatchers("api/models/**").permitAll()
                .requestMatchers("api/rentallocations/**").permitAll()
                .requestMatchers("api/rentals/**").permitAll()
                .requestMatchers("api/roles/**").permitAll()
                .requestMatchers("api/transmissions/**").permitAll()
                .requestMatchers("api/workers/**").permitAll()
                .anyRequest().authenticated()
            );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}

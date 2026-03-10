package com.realestate.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {

  http
   .csrf(csrf -> csrf.disable())
   .authorizeHttpRequests(auth -> auth
     .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/api/auth/**").permitAll()
     .requestMatchers("/auth/**").permitAll()
     .requestMatchers("/admin/**").hasRole("ADMIN")
     .requestMatchers("/seller/**").hasRole("SELLER")
     .requestMatchers("/appointments/**").hasRole("BUYER")
     .requestMatchers("/properties/**").permitAll()
     .anyRequest().authenticated()
   )
   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

  return http.build();
 }
 
 @Bean
 public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
 }
 
}

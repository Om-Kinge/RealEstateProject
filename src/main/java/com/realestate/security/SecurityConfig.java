
package com.realestate.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

  http
   .csrf(csrf -> csrf.disable())
   .authorizeHttpRequests(auth -> auth
     .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/api/auth/**").permitAll()
     .requestMatchers("/auth/**").permitAll()
     .requestMatchers("/admin/**").permitAll()
     .requestMatchers("/seller/**").permitAll()
     .requestMatchers("/appointments/**").permitAll()
     .requestMatchers("/properties/**").permitAll()
     .anyRequest().authenticated()
   );

  return http.build();
 }
 
 @Bean
 public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
 }
 
}

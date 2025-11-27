package com.BusBookingApp.config;

import com.BusBookingApp.security.JwtAuthenticationEntryPoint;
import com.BusBookingApp.security.JwtAuthenticationFilter;
import com.BusBookingApp.security.JwtAuthenticationEntryPoint;
import com.BusBookingApp.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->  auth
                        // ✅ Allow Swagger & API docs without authentication
                        //http://localhost:8083/swagger-ui/index.html
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers("/home/**").authenticated()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/free/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/staff/**").hasAnyRole("STAFF","ADMIN")
                        .requestMatchers("/owner/**").hasAnyRole("OWNER","STAFF","ADMIN")
                        .requestMatchers("/counter/**").hasAnyRole("COUNTER","OWNER","STAFF","ADMIN")
                        .requestMatchers("/agent/**").hasAnyRole("AGENT","OWNER","STAFF","ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER","AGENT","COUNTER","OWNER","STAFF","ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}

package com.example.BarsAndRestaurantsApp.config;

import com.example.BarsAndRestaurantsApp.domain.entities.UserEntity;
import com.example.BarsAndRestaurantsApp.repositories.UserRepository;
import com.example.BarsAndRestaurantsApp.security.ApplicationUserDetailsService;
import com.example.BarsAndRestaurantsApp.security.JwtAuthenticationFilter;
import com.example.BarsAndRestaurantsApp.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JwtAuthenticationFilter(authenticationService);
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        ApplicationUserDetailsService applicationUserDetailsService = new ApplicationUserDetailsService(userRepository);

        /*
        String email = "user@test.com";
        userRepository.findByUsername(email).orElseGet(() -> {
            UserEntity newUser = UserEntity.builder()
                    .username("Test User")
                    .password(passwordEncoder().encode("password"))
                    .build();
            return userRepository.save(newUser);
        });
        */

        return applicationUserDetailsService;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/product-image/**").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/api/v1/posts/**").authenticated()
                        .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(request -> {
                var corsConf = new org.springframework.web.cors.CorsConfiguration();
                        corsConf.setAllowedOrigins(List.of("http://localhost:4200"));
                        corsConf.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
                        corsConf.setAllowCredentials(true);
                        corsConf.setAllowedHeaders(List.of("*"));
                return corsConf;
            }))
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }
}

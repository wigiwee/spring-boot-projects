package com.elearn.app.elearn_bak.config.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.elearn.app.elearn_bak.config.AppConstants;
import com.elearn.app.elearn_bak.config.CustomAuthenticationEntryPoint;
import com.elearn.app.elearn_bak.dtos.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // @Bean
    // public UserDetailsService userDetailsService(){

    // // in memory user management
    // InMemoryUserDetailsManager userDetailsManager = new
    // InMemoryUserDetailsManager();

    // //creating user

    // userDetailsManager.createUser(
    // User.withDefaultPasswordEncoder()
    // .username("abc")
    // .password("123")
    // .roles("USER")
    // .build());

    // userDetailsManager.createUser(
    // User.withDefaultPasswordEncoder()
    // .username("admin")
    // .password("abc")
    // .roles("ADMIN")
    // .build());

    // return userDetailsManager;

    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // customization of the security filter chain

        // routes
        // httpSecurity
        // .authorizeHttpRequests( auth-> {
        // auth.requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll();
        // auth.requestMatchers(HttpMethod.GET,"/api/v1/courses/**").permitAll();
        // auth.requestMatchers("/client-login").permitAll();
        // auth.requestMatchers("/client-login-process").permitAll();
        // auth.requestMatchers("/login").permitAll();
        // auth.requestMatchers("/api/v1/users").permitAll();
        // auth.anyRequest().authenticated();
        // });
        // httpSecurity.cors(e -> e.disable());
        // manually customizing cors
        httpSecurity.cors(cors -> {
            CorsConfiguration config = new CorsConfiguration();
            //allowing single origin
            // config.addAllowedOrigin("http://localhost:5173");

            //allowing multiple origins
            config.setAllowedOrigins(List.of("http://localhost:5173","http://localhost:3000"));

            config.addAllowedMethod("*");
            config.addAllowedHeader("*");
            config.setAllowCredentials(true);
            config.setMaxAge(5 * 60L); // 5 minutes max age for preflight request (OPTIONS) to be cached by browser for future requests to same endpoint
            UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
            configurationSource.registerCorsConfiguration("/**", config);
            cors.configurationSource(configurationSource);

        });

        httpSecurity.csrf(e -> e.disable());

        httpSecurity.authorizeHttpRequests(auth -> {

            // auth.requestMatchers(HttpMethod.GET,
            // "/api/v1/**").hasAnyRole(AppConstants.ROLE_GUEST, AppConstants.ROLE_ADMIN)
            // .requestMatchers("/api/v1/categories/**").permitAll()
            // .requestMatchers("/api/v1/auth/**").permitAll()
            // .requestMatchers("/api/v1/**").hasRole(AppConstants.ROLE_ADMIN)
            // .requestMatchers(HttpMethod.POST,
            // "/api/v1/**").hasRole(AppConstants.ROLE_ADMIN)
            // .requestMatchers(HttpMethod.PUT,
            // "/api/v1/**").hasRole(AppConstants.ROLE_ADMIN)
            // .requestMatchers(HttpMethod.DELETE,
            // "/api/v1/**").hasRole(AppConstants.ROLE_ADMIN)
            // .anyRequest()
            // .authenticated();
            auth.requestMatchers("/**").permitAll();
        });
        // enables basic authentication
        httpSecurity.httpBasic(auth -> auth.authenticationEntryPoint(customAuthenticationEntryPoint));

        // httpSecurity.formLogin(Customizer.withDefaults()); //default configuration
        // for form login
        httpSecurity.formLogin( // Customizer.withDefaults()
                form -> {
                    // form.loginPage("/login");
                    // form.usernameParameter("username");
                    // form.passwordParameter("password");
                    // form.failureForwardUrl("/via-failure");
                    // form.successForwardUrl("/success");
                    // // form.loginProcessingUrl("/client-login");
                    // // form.loginProcessingUrl("/client-login-process");
                });

        httpSecurity.exceptionHandling(e -> e
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    CustomMessage customMessage = new CustomMessage();

                    customMessage.setMessage("You don't have permission to access this resource ! "
                            + accessDeniedException.getMessage());
                    customMessage.setSuccess(false);
                    String jsonString = new ObjectMapper().writeValueAsString(customMessage);
                    response.getWriter().write(jsonString);
                }));

        // httpSecurity.sessionManagement(e ->
        // e.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

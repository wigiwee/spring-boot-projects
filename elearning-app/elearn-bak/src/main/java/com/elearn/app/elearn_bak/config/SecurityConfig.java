package com.elearn.app.elearn_bak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity) throws Exception {

        //customization of the security filter chain
 
        //routes 
        httpSecurity
            .authorizeHttpRequests( auth-> {
            auth.requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll();
            // auth.requestMatchers(HttpMethod.GET,"/api/v1/courses/**").permitAll();
            auth.requestMatchers("/login").permitAll();

        });

        // enables basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // httpSecurity.formLogin(Customizer.withDefaults());  //default configuration for form login
        httpSecurity.formLogin(
            form ->{
                form.loginPage("/login");   //custom login page
                form.failureUrl("/login?error");  //redirect to this page if login fails
                form.usernameParameter("username");  //custom username parameter
                form.passwordParameter("password");  //custom password parameter
                form.defaultSuccessUrl("/home");  //redirect to this page if login is successful
                form.successForwardUrl("/api/v1/courses");  //redirect to this page if login is successful
            }
        );

        return httpSecurity.build();
    }
}

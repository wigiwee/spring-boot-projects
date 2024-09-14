package com.elearn.app.elearn_bak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService(){

    //     // in memory user management
    //     InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

    //     //creating user

    //     userDetailsManager.createUser(
    //         User.withDefaultPasswordEncoder()
    //         .username("abc")
    //         .password("123")
    //         .roles("USER")
    //         .build()); 

    //     userDetailsManager.createUser(
    //         User.withDefaultPasswordEncoder()
    //         .username("admin")
    //         .password("abc")
    //         .roles("ADMIN")
    //         .build());

    //     return userDetailsManager;

    // }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity) throws Exception {

        //customization of the security filter chain
 
        //routes 
        httpSecurity
            .authorizeHttpRequests( auth-> {
            // auth.requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll();
            auth.requestMatchers(HttpMethod.GET,"/api/v1/courses/**").permitAll();
            auth.requestMatchers("/client-login").permitAll();
            auth.requestMatchers("/client-login-process").permitAll();
            auth.requestMatchers("/login").permitAll();

        });
        
        // enables basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // httpSecurity.formLogin(Customizer.withDefaults());  //default configuration for form login
        httpSecurity.formLogin( // Customizer.withDefaults()
            form ->{
                // form.loginPage("/login");
                // form.usernameParameter("username");
                // form.passwordParameter("password");
                // form.failureForwardUrl("/via-failure");
                // form.successForwardUrl("/success");
                // // form.loginProcessingUrl("/client-login");
                // // form.loginProcessingUrl("/client-login-process");
            }
        );

        return httpSecurity.build();
    }
}

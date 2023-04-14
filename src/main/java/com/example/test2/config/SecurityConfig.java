package com.example.test2.config;

import com.example.test2.filter.UUIDAuthentificationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UUIDAuthentificationFilter uuidAuthentificationFilter;

    public SecurityConfig(UUIDAuthentificationFilter uuidAuthentificationFilter) {
        this.uuidAuthentificationFilter = uuidAuthentificationFilter;
    }

    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .anonymous()
                .and()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(uuidAuthentificationFilter, ExceptionTranslationFilter.class)
                .authorizeRequests()
                .antMatchers("/indicators/{serial}").permitAll()
                .anyRequest().authenticated();

        return httpSecurity.build();


    }
}
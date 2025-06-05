package com.vote.VotingApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        //

        httpSecurity.authorizeHttpRequests((request)->request
                .requestMatchers("/api/voters/*","/api/votes/*","/api/election-result/*").authenticated()
                .requestMatchers("/api/candidate/*").permitAll()
        );
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());

    return httpSecurity.build();
    }

}

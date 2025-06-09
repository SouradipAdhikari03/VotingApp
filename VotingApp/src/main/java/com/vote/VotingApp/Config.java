package com.vote.VotingApp;

import ch.qos.logback.core.joran.action.NOPAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {
@Autowired
    private UserDetailsService userDetailsService;

@Bean
public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    return daoAuthenticationProvider;
}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        //

        httpSecurity.authorizeHttpRequests((request)->request
                .requestMatchers("/api/voters/update/*","/api/voters/delete/*","/api/votes/*","/api/election-result/*").authenticated()
                .requestMatchers("/api/candidate/*","/api/voters/*").permitAll()
        );
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());


    return httpSecurity.build();
    }
//    public UserDetailsService userDetailsService(){
//
//    }

}

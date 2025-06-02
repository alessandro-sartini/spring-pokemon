package org.lessons.java.spring_pokemon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/pokemons/create", "/pokemons/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/types/create", "/types/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/types/delete/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/pokemons/delete/**").hasAuthority("ADMIN")
                .requestMatchers("/types", "/types/**").hasAuthority("ADMIN")
                .requestMatchers("/pokemons", "/pokemons/**").hasAnyAuthority("ADMIN", "USER")
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling()
                .and().csrf().disable();


                
                

        return http.build();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
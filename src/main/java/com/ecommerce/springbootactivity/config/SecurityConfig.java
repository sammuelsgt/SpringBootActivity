package com.ecommerce.springbootactivity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception {
        http.csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy((SessionCreationPolicy.ALWAYS)))
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/web/register/**", "/web/login/**","/web/home").permitAll()
                                .requestMatchers("/web/products/**","/web/products/").hasRole("SELLER")
                                .requestMatchers("/web/cartlist").hasRole("BUYER")

                                .anyRequest().permitAll()

                ).formLogin(
                        form -> form
                                .loginPage("/web/login")
                                .loginProcessingUrl("/web/login/success")
                                .defaultSuccessUrl("/web/home")
                                .failureUrl("/web/login")

                                .permitAll()
                ).logout(
                        logout -> logout
                                .deleteCookies("JSESSIONID")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/web/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}
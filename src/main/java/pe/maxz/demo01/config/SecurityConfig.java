package pe.maxz.demo01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/swagger-ui/**","/javainuse-openapi/**", "/v3/api-docs/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .build();
    }
 }

package guacci.dealership.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth->auth.requestMatchers("/admin/**").hasRole("ADMIN").
                requestMatchers("/employee/**").hasRole("EMPLOYEE").
                requestMatchers("/customer/**").hasRole("CUSTOMER").anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }
}

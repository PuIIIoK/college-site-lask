package puiiiokiq.collegelask.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("removal")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/register/**",
                        "/api/code/**",
                        "/api/auth/**",
                        "/api/slider/**",
                        "/api/home-info/**",
                        "/api/news/**",
                        "/api/formula/**").permitAll()
                .anyRequest().authenticated();  // Для всех остальных запросов требуется аутентификация

        return http.build();
    }


    // Бин для AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Создаем и возвращаем экземпляр BCryptPasswordEncoder
    }
}

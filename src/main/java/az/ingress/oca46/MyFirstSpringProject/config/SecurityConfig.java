package az.ingress.oca46.MyFirstSpringProject.config;

import az.ingress.oca46.MyFirstSpringProject.servis.CustomUserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration     // Bu bir konfiqurasiya sinfidir
@EnableWebSecurity // Spring Security-ni aktiv et
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserAuth userDetailsService;
    // ↑ Bizim custom service — Spring bunu authentication üçün istifadə edəcək

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ← tamamilə söndür

                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()))

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/auth/register").permitAll()
                        .requestMatchers("/swagger-ui-oca46/**").permitAll()
                        .requestMatchers("/swagger-ui-oca46/index.html").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/v1/api/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();}

}

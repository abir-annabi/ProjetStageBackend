//Configure la sécurité de l'application, incluant l'authentification JWT et les règles d'accès aux routes.
package com.projet.app.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projet.app.filters.JwtRequestFilter;


@Configuration //Indique que cette classe est une configuration Spring
@EnableWebSecurity //Active la sécurité Web fournie par Spring Security.
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        super();
        this.jwtRequestFilter = jwtRequestFilter;
    } //Le constructeur injecte un filtre personnalisé, JwtRequestFilter, qui intercepte les requêtes HTTP pour traiter les tokens JWT.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf -> csrf.disable()) // Désactive la protection CSRF.
                .authorizeRequests()
                .requestMatchers("/sendmail").hasRole("admin") // Autorise seulement les utilisateurs avec le rôle "admin" à accéder à "/sendmail".
                .requestMatchers("/signup", "/login").permitAll() // Autorise l'accès à "/signup" et "/login" sans authentification.
                .anyRequest().authenticated() // Exige une authentification pour toutes les autres requêtes.
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configure une gestion des sessions sans état (stateless : aucune session n'est maintenue côté serveur)).
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Ajoute le filtre JWT avant le filtre d'authentification.
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilise BCrypt pour encoder les mots de passe.
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); // Fournit le gestionnaire d'authentification.
    }

}

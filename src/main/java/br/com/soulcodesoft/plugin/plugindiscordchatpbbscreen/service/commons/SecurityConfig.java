package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;


    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register", "/css/**").permitAll() // Permite acesso à página de registro e arquivos de estilo
                        .anyRequest()
                        .authenticated() // Qualquer outra requisição precisa de autenticação
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login customizada
                        .defaultSuccessUrl("/home", true)
                        .permitAll() // Permite acesso a todos a página de login
                )
                .logout(logout -> logout.permitAll()) // Permite logout a todos
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/403")); // Página de acesso negado, se necessário

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}

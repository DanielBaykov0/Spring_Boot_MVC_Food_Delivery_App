package baykov.daniel.fooddelivery.config;

import baykov.daniel.fooddelivery.domain.constant.RoleEnum;
import baykov.daniel.fooddelivery.repository.UserRepository;
import baykov.daniel.fooddelivery.service.FoodDeliveryUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/", "/users/login", "/users/register").permitAll()
                                .requestMatchers("/orders/all").hasAnyRole(RoleEnum.WORKER.name(), RoleEnum.ADMIN.name())
                                .anyRequest().authenticated())
                .formLogin(login ->
                        login.loginPage("/users/login")
                                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                                .defaultSuccessUrl("/", true)
                                .failureForwardUrl("/users/login-error"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new FoodDeliveryUserDetailsService(userRepository);
    }
}

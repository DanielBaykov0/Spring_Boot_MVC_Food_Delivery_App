package baykov.daniel.fooddelivery.config;

import baykov.daniel.fooddelivery.domain.constant.RoleEnum;
import baykov.daniel.fooddelivery.repository.UserRepository;
import baykov.daniel.fooddelivery.service.FoodDeliveryUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers(mvc.pattern("/")).permitAll()
                                .requestMatchers(mvc.pattern("/users/login")).permitAll()
                                .requestMatchers(mvc.pattern("/users/register")).permitAll()
                                .requestMatchers(mvc.pattern("/users/login-error")).permitAll()
                                .requestMatchers(mvc.pattern("/contact")).permitAll()
                                .requestMatchers(mvc.pattern("/menu/")).permitAll()
                                .requestMatchers(mvc.pattern("/menu/**")).permitAll()
                                .requestMatchers(mvc.pattern("/api/orders/history")).permitAll()
                                .requestMatchers(mvc.pattern("/cart")).hasRole(RoleEnum.USER.name())
                                .requestMatchers(mvc.pattern("/cart/add/**")).hasRole(RoleEnum.USER.name())
                                .requestMatchers(mvc.pattern("/orders/**")).hasRole(RoleEnum.USER.name())
                                .requestMatchers(mvc.pattern("/orders/all/history")).hasAnyRole(RoleEnum.WORKER.name(), RoleEnum.ADMIN.name())
                                .anyRequest().authenticated())
                .formLogin(login ->
                        login.loginPage("/users/login")
                                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                                .defaultSuccessUrl("/")
                                .failureForwardUrl("/users/login-error"))
                .logout(logout ->
                        logout.logoutUrl("/users/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
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

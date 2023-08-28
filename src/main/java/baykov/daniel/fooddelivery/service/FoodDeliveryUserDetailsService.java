package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.entity.Role;
import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static baykov.daniel.fooddelivery.constants.ErrorMessages.USER_NOT_FOUND_WITH_EMAIL;
import static baykov.daniel.fooddelivery.constants.Messages.ROLE;

@AllArgsConstructor
public class FoodDeliveryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findUserByEmailIgnoreCase(email)
                .map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_WITH_EMAIL + email));
    }

    private UserDetails mapToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                extractAuthorities(user));
    }

    private List<GrantedAuthority> extractAuthorities(User user) {
        return user
                .getRoles()
                .stream()
                .map(this::mapRole)
                .toList();
    }

    private GrantedAuthority mapRole(Role role) {
        return new SimpleGrantedAuthority(ROLE + role.getRole().name());
    }
}

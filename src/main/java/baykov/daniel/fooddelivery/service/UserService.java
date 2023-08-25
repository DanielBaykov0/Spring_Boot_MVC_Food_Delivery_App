package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.RoleEnum;
import baykov.daniel.fooddelivery.domain.dto.binding.RegistrationBindingDto;
import baykov.daniel.fooddelivery.domain.dto.view.UserViewDto;
import baykov.daniel.fooddelivery.domain.entity.Role;
import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CartService cartService;

    public void register(RegistrationBindingDto registrationBindingDto) {
        User user = this.mapToUser(registrationBindingDto);

        Role userRole = new Role(RoleEnum.USER);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(userRole));
        user.setCart(this.cartService.getNewCart());

        this.userRepository.saveAndFlush(user);
    }

    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username).orElse(null);
    }

    public UserViewDto getUserViewDtoByUsername(String username) {
        return mapToUserView(this.userRepository.findByUsername(username));
    }

    public UserViewDto getUserById(Long id) {
        User user = this.userRepository.findUserById(id);
        return this.mapToUserView(user);
    }

    public List<UserViewDto> getAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .map(this::mapToUserView)
                .collect(Collectors.toList());
    }

    public User mapToUser(RegistrationBindingDto registrationBindingDto) {
        return this.modelMapper.map(registrationBindingDto, User.class);
    }

    public UserViewDto mapToUserView(User user) {
        return this.modelMapper.map(user, UserViewDto.class);
    }
}

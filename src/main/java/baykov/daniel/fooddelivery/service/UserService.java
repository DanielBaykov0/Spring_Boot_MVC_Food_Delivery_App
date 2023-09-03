package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.RoleEnum;
import baykov.daniel.fooddelivery.domain.dto.binding.EditUserBindingDto;
import baykov.daniel.fooddelivery.domain.dto.binding.RegistrationBindingDto;
import baykov.daniel.fooddelivery.domain.dto.view.UserViewDto;
import baykov.daniel.fooddelivery.domain.entity.Role;
import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.exception.ObjectNotFoundException;
import baykov.daniel.fooddelivery.exception.ResourceNotFoundException;
import baykov.daniel.fooddelivery.repository.RoleRepository;
import baykov.daniel.fooddelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.*;
import static baykov.daniel.fooddelivery.constant.Messages.ROLE_WORKER;

@Service
@AllArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final CartService cartService;

    public void register(RegistrationBindingDto registrationBindingDto) {
        User user = this.mapToUser(registrationBindingDto);

        Role userRole = this.roleService.getRole(RoleEnum.ROLE_USER);

        user
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRoles(new ArrayList<>(Collections.singletonList(userRole)))
                .setCart(this.cartService.getNewCart());

        this.userRepository.saveAndFlush(user);
    }

    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(USER, USERNAME, username));
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findUserByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException(USER, EMAIL, email));
    }

    public UserViewDto getUserViewDtoByEmail(String email) {
        return mapToUserView(this.userRepository.findUserByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException(USER, EMAIL, email)));
    }

    public UserViewDto getUserById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(USER, ID, id));
        return this.mapToUserView(user);
    }

    public List<UserViewDto> getAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .map(this::mapToUserView)
                .toList();
    }

    public void editUser(Long id, EditUserBindingDto editUserBindingDto) {
        User user = this.userRepository.findUserById(id);
        user
                .setFirstName(editUserBindingDto.getFirstName())
                .setLastName(editUserBindingDto.getLastName());
        this.userRepository.saveAndFlush(user);
    }

    public void removeRole(Long userId) {
        User user = this.userRepository.findUserById(userId);
        user
                .getRoles()
                .removeIf(userRole -> userRole.getRole().name().equals(ROLE_WORKER));
        this.userRepository.saveAndFlush(user);
    }

    public void addRole(Long userId) {
        User user = this.userRepository.findUserById(userId);
        user.getRoles().add(roleRepository.findByRole(RoleEnum.ROLE_WORKER).get());
        this.userRepository.saveAndFlush(user);
    }

    public User mapToUser(RegistrationBindingDto registrationBindingDto) {
        return this.modelMapper.map(registrationBindingDto, User.class);
    }

    public UserViewDto mapToUserView(User user) {
        return this.modelMapper.map(user, UserViewDto.class);
    }
}

package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.RoleEnum;
import baykov.daniel.fooddelivery.domain.dto.binding.RegistrationBindingDto;
import baykov.daniel.fooddelivery.domain.dto.model.UserModelDto;
import baykov.daniel.fooddelivery.domain.entity.Role;
import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void register(UserModelDto userModelDto) {
        User user = this.mapToUser(userModelDto);

        Role userRole = new Role(RoleEnum.USER);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(userRole));

        this.userRepository.saveAndFlush(user);
    }

    public UserModelDto mapToModel(RegistrationBindingDto registrationBindingDto) {
        return this.modelMapper.map(registrationBindingDto, UserModelDto.class);
    }

    public User mapToUser(UserModelDto userModelDto) {
        return this.modelMapper.map(userModelDto, User.class);
    }
}

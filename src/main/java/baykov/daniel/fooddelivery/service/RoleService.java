package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.RoleEnum;
import baykov.daniel.fooddelivery.domain.entity.Role;
import baykov.daniel.fooddelivery.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(RoleEnum roleEnum) {
        Optional<Role> optionalRole = this.roleRepository.findByRole(roleEnum);
        Role role = new Role();
        if (optionalRole.isPresent()) {
            role = optionalRole.get();
        }
        return role;
    }
}

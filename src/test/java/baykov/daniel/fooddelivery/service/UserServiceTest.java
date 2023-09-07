package baykov.daniel.fooddelivery.service;


import baykov.daniel.fooddelivery.exception.ObjectNotFoundException;
import baykov.daniel.fooddelivery.repository.RoleRepository;
import baykov.daniel.fooddelivery.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private CartService cartService;


    @BeforeEach
    void setUp() {
        userService = new UserService(modelMapper, passwordEncoder, userRepository, roleRepository, roleService, cartService);
    }

    @Test
    void testGetUserById_NotSuccessful() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> this.userService.getUserById(-1L));
    }


}

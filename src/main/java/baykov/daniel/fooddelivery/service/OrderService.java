package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.entity.Product;
import baykov.daniel.fooddelivery.domain.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final UserService userService;

    public List<Product> getProducts(Principal principal) {
        User user = this.userService.getUserByUsername(principal.getName());
        return user.getCart().getProducts();
    }
}

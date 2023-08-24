package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.dto.view.ProductViewDto;
import baykov.daniel.fooddelivery.domain.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public List<ProductViewDto> getProducts(Principal principal) {
        User user = this.userService.getUserByUsername(principal.getName());
        return user
                .getCart()
                .getProducts()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewDto.class))
                .collect(Collectors.toList());
    }
}

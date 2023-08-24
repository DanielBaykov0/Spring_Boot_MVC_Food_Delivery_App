package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.dto.model.UserModelDto;
import baykov.daniel.fooddelivery.domain.dto.view.ProductViewDto;
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
        UserModelDto user =
                this.modelMapper
                        .map(userService.getUserByUsername(principal.getName()), UserModelDto.class);
        return user
                .getCart()
                .getProducts()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewDto.class))
                .collect(Collectors.toList());
    }
}

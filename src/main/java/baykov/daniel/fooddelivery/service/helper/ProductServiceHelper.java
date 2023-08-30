package baykov.daniel.fooddelivery.service.helper;

import baykov.daniel.fooddelivery.domain.dto.binding.AddProductBindingDto;
import baykov.daniel.fooddelivery.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceHelper {

    public static Product createProduct(AddProductBindingDto addProductBindingDto) {
        Product product = new Product();
        product
                .setName(addProductBindingDto.getName())
                .setCategory(addProductBindingDto.getCategory())
                .setDescription(addProductBindingDto.getDescription())
                .setPrice(addProductBindingDto.getPrice());
        return product;
    }
}

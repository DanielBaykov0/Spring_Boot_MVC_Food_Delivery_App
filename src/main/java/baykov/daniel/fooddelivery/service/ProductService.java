package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import baykov.daniel.fooddelivery.domain.dto.view.ProductViewDto;
import baykov.daniel.fooddelivery.domain.entity.Product;
import baykov.daniel.fooddelivery.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductViewDto> getAllProducts(ProductCategoryEnum category) {
        return this.productRepository
                .findAllProductsByCategory(category)
                .stream()
                .map(this::mapToViewDto)
                .collect(Collectors.toList());
    }

    public String getProductCategory(Long id) {
        return this.productRepository.findById(id).get().getCategory().name();
    }

    private ProductViewDto mapToViewDto(Product product) {
        return this.modelMapper.map(product, ProductViewDto.class);
    }
}

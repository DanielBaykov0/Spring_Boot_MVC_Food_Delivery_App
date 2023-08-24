package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import baykov.daniel.fooddelivery.domain.entity.Product;
import baykov.daniel.fooddelivery.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<Product> getAllProducts(ProductCategoryEnum productCategoryEnum) {
        return this.productRepository.findAllProductsByCategory(productCategoryEnum);
    }

    public String getProductCategory(Long id) {
        return this.productRepository.findById(id).get().getCategory().name();
    }
}

package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import baykov.daniel.fooddelivery.domain.dto.binding.EditProductBindingDto;
import baykov.daniel.fooddelivery.domain.dto.binding.AddProductBindingDto;
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

    public ProductViewDto getProductById(Long productId) {
        Product product = this.productRepository.findProductById(productId);
        return this.modelMapper.map(product, ProductViewDto.class);
    }

    public void editProduct(Long productId, EditProductBindingDto editProductBindingDto) {
        Product product = this.productRepository.findProductById(productId);
        product
                .setDescription(editProductBindingDto.getDescription())
                .setPrice(editProductBindingDto.getPrice());

        this.productRepository.save(product);
    }

    public void addProduct(AddProductBindingDto addProductBindingDto) {
        Product product = new Product();
        product
                .setName(addProductBindingDto.getName())
                .setCategory(addProductBindingDto.getCategory())
                .setDescription(addProductBindingDto.getDescription())
                .setPrice(addProductBindingDto.getPrice());

        this.productRepository.saveAndFlush(product);
    }

    public String getProductCategory(Long id) {
        return this.productRepository.findById(id).get().getCategory().name();
    }

    public void deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

    private ProductViewDto mapToViewDto(Product product) {
        return this.modelMapper.map(product, ProductViewDto.class);
    }
}

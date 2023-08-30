package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import baykov.daniel.fooddelivery.domain.dto.binding.AddProductBindingDto;
import baykov.daniel.fooddelivery.domain.dto.binding.EditProductBindingDto;
import baykov.daniel.fooddelivery.domain.dto.view.ProductViewDto;
import baykov.daniel.fooddelivery.domain.entity.Product;
import baykov.daniel.fooddelivery.exception.ObjectNotFoundException;
import baykov.daniel.fooddelivery.exception.WrongCategoryException;
import baykov.daniel.fooddelivery.repository.ProductRepository;
import baykov.daniel.fooddelivery.service.helper.ProductServiceHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.ID;
import static baykov.daniel.fooddelivery.constant.Messages.PRODUCT;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductViewDto> getAllProductsByCategory(ProductCategoryEnum category) {
        return this.productRepository
                .findAllProductsByCategory(category)
                .stream()
                .map(this::mapToViewDto)
                .collect(Collectors.toList());
    }

    public ProductViewDto getProductById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(PRODUCT, ID, id));
        return this.modelMapper.map(product, ProductViewDto.class);
    }

    public void editProduct(Long id, EditProductBindingDto editProductBindingDto) {
        Product product = this.productRepository.findProductById(id);
        product
                .setDescription(editProductBindingDto.getDescription())
                .setPrice(editProductBindingDto.getPrice());
        this.productRepository.save(product);
    }

    public void addProduct(AddProductBindingDto addProductBindingDto) {
        Product product = ProductServiceHelper.createProduct(addProductBindingDto);
        this.productRepository.saveAndFlush(product);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    public String getProductCategory(Long id) {
        return this.productRepository
                .findProductById(id)
                .getCategory()
                .name();
    }

    public ProductCategoryEnum findCategory(String category) {
        for (ProductCategoryEnum categoryEnum : ProductCategoryEnum.values()) {
            if (categoryEnum.name().equals(category)) {
                return categoryEnum;
            }
        }

        throw new WrongCategoryException(category);
    }

    private ProductViewDto mapToViewDto(Product product) {
        return this.modelMapper.map(product, ProductViewDto.class);
    }
}

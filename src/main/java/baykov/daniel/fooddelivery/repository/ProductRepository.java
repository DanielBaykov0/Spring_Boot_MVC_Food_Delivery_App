package baykov.daniel.fooddelivery.repository;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import baykov.daniel.fooddelivery.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllProductsByCategory(ProductCategoryEnum productCategoryEnum);

    Product findProductById(Long id);
}

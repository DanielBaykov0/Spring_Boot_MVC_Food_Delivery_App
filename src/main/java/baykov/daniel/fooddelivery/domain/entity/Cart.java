package baykov.daniel.fooddelivery.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    private Long productsCount = 0L;

    private BigDecimal productsSum = BigDecimal.ZERO;

    public void addProduct(Product product) {
        this.getProducts().add(product);
    }

    public void increaseProductsSum(BigDecimal productPrice) {
        BigDecimal sum = this.getProductsSum();
        this.setProductsSum(sum.add(productPrice));
        this.productsCount++;
    }

    public void decreaseProductsSum(BigDecimal productPrice) {
        BigDecimal sum = this.getProductsSum();
        this.setProductsSum(sum.subtract(productPrice));
        this.productsCount--;
    }
}

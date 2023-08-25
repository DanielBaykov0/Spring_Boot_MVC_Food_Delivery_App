package baykov.daniel.fooddelivery.domain.dto.view;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private ProductCategoryEnum category;
}

package baykov.daniel.fooddelivery.domain.dto.binding;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBindingDto {

    @NotEmpty(message = "Please enter a name.")
    private String name;

    @Positive(message = "Price must be positive.")
    @NotNull(message = "Please enter a price.")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductCategoryEnum category;

    @NotEmpty(message = "Please enter a description.")
    private String description;
}

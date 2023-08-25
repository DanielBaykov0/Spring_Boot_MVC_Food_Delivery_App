package baykov.daniel.fooddelivery.domain.dto.binding;

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
public class EditProductBindingDto {

    @NotEmpty(message = "Cannot remove desctiption.")
    private String description;

    @Positive(message = "Price must be positive.")
    @NotNull(message = "Please enter a price.")
    private BigDecimal price;
}

package baykov.daniel.fooddelivery.domain.dto.binding;

import baykov.daniel.fooddelivery.validation.common.ValidPersonName;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static baykov.daniel.fooddelivery.constant.ErrorMessages.FIRST_NAME_REQUIRED;
import static baykov.daniel.fooddelivery.constant.ErrorMessages.LAST_NAME_REQUIRED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditUserBindingDto {

    @NotEmpty(message = FIRST_NAME_REQUIRED)
    @ValidPersonName
    private String firstName;

    @NotEmpty(message = LAST_NAME_REQUIRED)
    @ValidPersonName
    private String lastName;
}

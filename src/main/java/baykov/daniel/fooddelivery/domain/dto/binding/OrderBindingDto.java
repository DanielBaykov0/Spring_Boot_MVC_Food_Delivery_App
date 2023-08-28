package baykov.daniel.fooddelivery.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static baykov.daniel.fooddelivery.constants.ErrorMessages.ADDRESS_REQUIRED;
import static baykov.daniel.fooddelivery.constants.ErrorMessages.CONTACT_PHONE_NUMBER_REQUIRED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBindingDto {

    private String comment;

    @NotEmpty(message = ADDRESS_REQUIRED)
    private String address;

    @NotEmpty(message = CONTACT_PHONE_NUMBER_REQUIRED)
    private String contactPhoneNumber;
}

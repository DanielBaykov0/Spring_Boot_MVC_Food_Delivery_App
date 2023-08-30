package baykov.daniel.fooddelivery.domain.dto.binding;

import baykov.daniel.fooddelivery.validation.common.ValidPhoneNumber;
import baykov.daniel.fooddelivery.validation.order.DiscountMatch;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static baykov.daniel.fooddelivery.constant.ErrorMessages.ADDRESS_REQUIRED;
import static baykov.daniel.fooddelivery.constant.ErrorMessages.CONTACT_PHONE_NUMBER_REQUIRED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBindingDto {

    private String comment;

    @NotEmpty(message = ADDRESS_REQUIRED)
    private String address;

    @NotEmpty(message = CONTACT_PHONE_NUMBER_REQUIRED)
    @ValidPhoneNumber
    private String contactPhoneNumber;

    @DiscountMatch
    private String discount;
}

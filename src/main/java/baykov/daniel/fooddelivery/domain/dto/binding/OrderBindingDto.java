package baykov.daniel.fooddelivery.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBindingDto {

    private String comment;

    @NotEmpty(message = "Please enter an address.")
    private String address;

    @NotEmpty(message = "Please enter a contact phone number.")
    private String contactPhoneNumber;
}

package baykov.daniel.fooddelivery.domain.dto.binding;

import baykov.daniel.fooddelivery.validation.common.ValidEmail;
import baykov.daniel.fooddelivery.validation.common.ValidPersonName;
import baykov.daniel.fooddelivery.validation.common.ValidPhoneNumber;
import baykov.daniel.fooddelivery.validation.user.ValidUserEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static baykov.daniel.fooddelivery.constant.ErrorMessages.*;

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

    @NotEmpty(message = USERNAME_REQUIRED)
    @Size(min = 4, message = USERNAME_MINIMUM)
    private String username;

    @NotEmpty(message = EMAIL_REQUIRED)
    @ValidUserEmail(message = EMAIL_UNIQUE)
    @ValidEmail
    private String email;

    @Positive
    @NotNull(message = AGE_REQUIRED)
    private Integer age;

    @NotEmpty(message = PHONE_NUMBER_REQUIRED)
    @ValidPhoneNumber
    private String phoneNumber;
}

package baykov.daniel.fooddelivery.domain.dto.binding;

import baykov.daniel.fooddelivery.domain.constant.GenderEnum;
import baykov.daniel.fooddelivery.validation.user.FieldMatch;
import baykov.daniel.fooddelivery.validation.user.ValidUserEmail;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static baykov.daniel.fooddelivery.constant.ErrorMessages.*;
import static baykov.daniel.fooddelivery.constant.Messages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(firstField = PASSWORD,
        secondField = CONFIRM_PASSWORD,
        message = MATCHING_PASSWORDS)
public class RegistrationBindingDto {

    @NotEmpty(message = FIRST_NAME_REQUIRED)
    @Size(min = 2, max = 15, message = FIRST_NAME_BETWEEN_2_15)
    private String firstName;

    @NotEmpty(message = LAST_NAME_REQUIRED)
    @Size(min = 2, max = 15, message = LAST_NAME_BETWEEN_2_15)
    private String lastName;

    @NotEmpty(message = USERNAME_REQUIRED)
    @Size(min = 4, message = USERNAME_MINIMUM)
    private String username;

    @NotEmpty(message = EMAIL_REQUIRED)
    @ValidUserEmail(message = EMAIL_UNIQUE)
    private String email;

    @NotEmpty(message = PASSWORD_REQUIRED)
    @Size(min = 8, message = PASSWORD_MINIMUM)
    private String password;

    private String confirmPassword;

    @Positive
    @NotNull(message = AGE_REQUIRED)
    private Integer age;

    @NotEmpty(message = PHONE_NUMBER_REQUIRED)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;
}

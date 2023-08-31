package baykov.daniel.fooddelivery.domain.dto.binding;

import baykov.daniel.fooddelivery.domain.constant.GenderEnum;
import baykov.daniel.fooddelivery.validation.common.ValidEmail;
import baykov.daniel.fooddelivery.validation.common.ValidPersonName;
import baykov.daniel.fooddelivery.validation.common.ValidPhoneNumber;
import baykov.daniel.fooddelivery.validation.user.PasswordValueMatches;
import baykov.daniel.fooddelivery.validation.user.ValidPassword;
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
import static baykov.daniel.fooddelivery.constant.Messages.CONFIRM_PASSWORD;
import static baykov.daniel.fooddelivery.constant.Messages.PASSWORD;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordValueMatches.List({
        @PasswordValueMatches(
                field = PASSWORD,
                fieldMatch = CONFIRM_PASSWORD
        )
})
public class RegistrationBindingDto {

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

    @NotEmpty(message = PASSWORD_REQUIRED)
    @Size(min = 8, message = PASSWORD_MINIMUM)
    @ValidPassword
    private String password;

    @ValidPassword
    private String confirmPassword;

    @Positive
    @NotNull(message = AGE_REQUIRED)
    private Integer age;

    @NotEmpty(message = PHONE_NUMBER_REQUIRED)
    @ValidPhoneNumber
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;
}

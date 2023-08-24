package baykov.daniel.fooddelivery.domain.dto.binding;

import baykov.daniel.fooddelivery.domain.constant.GenderEnum;
import baykov.daniel.fooddelivery.validation.FieldMatch;
import baykov.daniel.fooddelivery.validation.ValidUserEmail;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(firstField = "password",
        secondField = "confirmPassword",
        message = "Passwords should match.")
public class RegistrationBindingDto {

    @NotEmpty(message = "Please enter first name.")
    @Size(min = 2, max = 15, message = "First name must be between 2 and 15 characters.")
    private String firstName;

    @NotEmpty(message = "Please enter last name.")
    @Size(min = 2, max = 15, message = "Last name must be between 2 and 15 characters.")
    private String lastName;

    @NotEmpty(message = "Please enter username.")
    @Size(min = 4, message = "Username must be at least 4 characters.")
    private String username;

    @NotEmpty(message = "Please enter email.")
    @ValidUserEmail(message = "Email should be unique.")
    private String email;

    @NotEmpty(message = "Please enter password.")
    @Size(min = 8, message = "Password must be at least 8 characters.")
    private String password;

    private String confirmPassword;

    @Positive
    @NotNull(message = "Please enter age.")
    private Integer age;

    @NotEmpty(message = "Please enter phoneNumber.")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;
}

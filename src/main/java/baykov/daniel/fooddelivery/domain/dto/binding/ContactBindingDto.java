package baykov.daniel.fooddelivery.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
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
public class ContactBindingDto {

    @NotEmpty(message = NAME_REQUIRED)
    @Size(min = 2, max = 15, message = NAME_BETWEEN_2_15)
    private String name;

    @NotEmpty(message = EMAIL_REQUIRED)
    private String email;

    @NotEmpty(message = SUBJECT_REQUIRED)
    @Size(min = 2, message = SUBJECT_MINIMUM)
    private String subject;

    @NotEmpty(message = DESCRIPTION_REQUIRED)
    @Size(min = 10, max = 2000, message = DESCRIPTION_BETWEEN_10_2000)
    private String description;
}

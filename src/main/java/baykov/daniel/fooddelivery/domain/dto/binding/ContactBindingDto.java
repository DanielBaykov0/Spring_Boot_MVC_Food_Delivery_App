package baykov.daniel.fooddelivery.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactBindingDto {

    @NotEmpty(message = "Please enter a name.")
    @Size(min = 2, max = 15, message = "Name must be between 2 and 15 characters.")
    private String name;

    @NotEmpty(message = "Please enter an email.")
    private String email;

    @NotEmpty(message = "Please enter a subject.")
    @Size(min = 2, message = "Subject must be at least 2 characters.")
    private String subject;

    @NotEmpty(message = "Please enter a description.")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters.")
    private String description;
}

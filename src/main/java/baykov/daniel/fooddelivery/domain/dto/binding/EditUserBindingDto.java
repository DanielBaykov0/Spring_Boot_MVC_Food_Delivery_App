package baykov.daniel.fooddelivery.domain.dto.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditUserBindingDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Integer age;
    private String phoneNumber;
}

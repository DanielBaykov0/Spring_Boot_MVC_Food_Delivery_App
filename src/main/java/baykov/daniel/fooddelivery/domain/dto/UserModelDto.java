package baykov.daniel.fooddelivery.domain.dto;

import baykov.daniel.fooddelivery.domain.constant.GenderEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModelDto {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private String age;

    @Enumerated(EnumType.STRING)
    private String phoneNumber;

    private GenderEnum gender;
}

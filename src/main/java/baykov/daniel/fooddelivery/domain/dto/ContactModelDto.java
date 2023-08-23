package baykov.daniel.fooddelivery.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactModelDto {

    private String name;
    private String email;
    private String subject;
    private String description;
    private LocalDateTime createdOn;
}

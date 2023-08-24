package baykov.daniel.fooddelivery.domain.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentModelDto {

    private String description;
    private UserModelDto user;
    private OrderModelDto order;
}

package baykov.daniel.fooddelivery.domain.dto.model;

import baykov.daniel.fooddelivery.domain.constant.GenderEnum;
import baykov.daniel.fooddelivery.domain.entity.Order;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModelDto {

    private String firstName;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private CartModelDto cart;

    private Set<Order> orders;
}

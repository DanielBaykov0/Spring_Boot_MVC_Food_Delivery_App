package baykov.daniel.fooddelivery.domain.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderModelDto {

    private UserModelDto owner;
    private BigDecimal price;
    private CartModelDto cart;
    private LocalDateTime createdOn;
    private LocalDateTime deliveredOn;
    private List<CommentModelDto> comments;
    private String contactPhoneNumber;
    private Boolean isDelivered;
}

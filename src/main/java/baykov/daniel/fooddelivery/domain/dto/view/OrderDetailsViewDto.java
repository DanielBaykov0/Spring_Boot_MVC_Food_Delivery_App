package baykov.daniel.fooddelivery.domain.dto.view;

import baykov.daniel.fooddelivery.domain.constant.OrderStatusEnum;
import baykov.daniel.fooddelivery.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsViewDto {

    private Long id;
    private BigDecimal price;
    private String comment;
    private String address;
    private String contactPhoneNumber;
    private String discount;
    private String client;
    private Cart cart;
    private LocalDateTime createdOn;
    private LocalDateTime deliveredOn;
    private Boolean isDelivered;
    private OrderStatusEnum status;
}

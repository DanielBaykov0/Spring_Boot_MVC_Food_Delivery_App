package baykov.daniel.fooddelivery.service.helper;

import baykov.daniel.fooddelivery.domain.constant.OrderStatusEnum;
import baykov.daniel.fooddelivery.domain.dto.binding.OrderBindingDto;
import baykov.daniel.fooddelivery.domain.entity.Order;
import baykov.daniel.fooddelivery.domain.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static baykov.daniel.fooddelivery.constant.ErrorMessages.NO_COMMENT;

@Component
public class OrderServiceHelper {

    public static void buildOrder(OrderBindingDto orderBindingDto, Order order, User user) {
        BigDecimal price = user
                .getCart()
                .getProductsSum()
                .add(BigDecimal.valueOf(user.getCart().getProductsCount() * 0.5))
                .add(BigDecimal.valueOf(5.00));

        price = orderBindingDto
                .getDiscount()
                .isEmpty() ? price : price.multiply(BigDecimal.valueOf(0.9));

        order
                .setOwner(user)
                .setPrice(price)
                .setDiscount(orderBindingDto.getDiscount())
                .setComment(orderBindingDto.getComment() != null ? orderBindingDto.getComment() : NO_COMMENT)
                .setAddress(orderBindingDto.getAddress())
                .setContactPhoneNumber(orderBindingDto.getContactPhoneNumber())
                .setCreatedOn(LocalDateTime.now())
                .setStatus(OrderStatusEnum.IN_PROGRESS);
    }
}

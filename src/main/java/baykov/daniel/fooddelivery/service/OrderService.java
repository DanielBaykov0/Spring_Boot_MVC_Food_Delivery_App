package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.constant.OrderStatusEnum;
import baykov.daniel.fooddelivery.domain.dto.binding.OrderBindingDto;
import baykov.daniel.fooddelivery.domain.dto.view.OrderDetailsViewDto;
import baykov.daniel.fooddelivery.domain.dto.view.ProductViewDto;
import baykov.daniel.fooddelivery.domain.entity.Order;
import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.exception.ObjectNotFoundException;
import baykov.daniel.fooddelivery.repository.OrderRepository;
import baykov.daniel.fooddelivery.service.helper.OrderServiceHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.ID;
import static baykov.daniel.fooddelivery.constant.ErrorMessages.NO_COMMENT;
import static baykov.daniel.fooddelivery.constant.Messages.ORDER;

@Service
@AllArgsConstructor
public class OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<ProductViewDto> getProducts(String email) {
        User user = this.userService.getUserByEmail(email);
        return user
                .getCart()
                .getProducts()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewDto.class))
                .collect(Collectors.toList());
    }

    public BigDecimal getProductsPrice(String email) {
        User user = this.userService.getUserByEmail(email);
        return user.getCart().getProductsSum();
    }

    @Transactional
    public void makeOrder(OrderBindingDto orderBindingDto, String email) {
        Order order = new Order();
        User user = this.userService.getUserByEmail(email);

        OrderServiceHelper.buildOrder(orderBindingDto, order, user);

        this.orderRepository.saveAndFlush(order);
        user.getCart()
                .setProducts(new ArrayList<>())
                .setProductsSum(BigDecimal.ZERO)
                .setProductsCount(0L);
    }

    @Transactional
    public List<OrderDetailsViewDto> getOrdersByUser(String email) {
        User user = this.userService.getUserByEmail(email);
        return this.orderRepository
                .findAllOrdersByOwnerId(user.getId())
                .stream()
                .map(this::mapToOrderViewDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<OrderDetailsViewDto> getInProgressOrdersByUser(User user) {
        return this.orderRepository
                .findAllOrdersByStatusAndOwnerId(user.getId(), OrderStatusEnum.IN_PROCESS)
                .stream()
                .map(this::mapToOrderViewDto)
                .collect(Collectors.toList());
    }

    public OrderDetailsViewDto getOrderById(Long id) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ORDER, ID, id));

        if (order.getComment().isEmpty()) {
            order.setComment(NO_COMMENT);
        }

        return mapToOrderViewDto(order);
    }

    public List<OrderDetailsViewDto> getAllOrders() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(this::mapToOrderViewDto)
                .collect(Collectors.toList());
    }

    public void finishOrder(Long id) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ORDER, ID, id));
        order.setStatus(OrderStatusEnum.DELIVERED);
        order.setDeliveredOn(LocalDateTime.now());
        this.orderRepository.saveAndFlush(order);
    }

    public void cancelOrder(Long id) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ORDER, ID, id));
        order.setStatus(OrderStatusEnum.CANCELLED);
        this.orderRepository.saveAndFlush(order);
    }

    private OrderDetailsViewDto mapToOrderViewDto(Order order) {
        OrderDetailsViewDto orderDetailsViewDto = this.modelMapper.map(order, OrderDetailsViewDto.class);
        orderDetailsViewDto.setClient(order.getOwner().getUsername());
        return orderDetailsViewDto;
    }
}

package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.dto.binding.OrderBindingDto;
import baykov.daniel.fooddelivery.domain.dto.view.OrderDetailsViewDto;
import baykov.daniel.fooddelivery.domain.dto.view.ProductViewDto;
import baykov.daniel.fooddelivery.domain.entity.Order;
import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<ProductViewDto> getProducts(Principal principal) {
        User user = this.userService.getUserByUsername(principal.getName());
        return user
                .getCart()
                .getProducts()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewDto.class))
                .collect(Collectors.toList());
    }

    public BigDecimal getProductsPrice(Principal principal) {
        User user = this.userService.getUserByUsername(principal.getName());
        return user.getCart().getProductsSum();
    }

    @Transactional
    public void makeOrder(OrderBindingDto orderBindingDto, Principal principal) {
        Order order = new Order();
        User user = this.userService.getUserByUsername(principal.getName());

        order
                .setOwner(user)
                .setPrice(user.getCart().getProductsSum())
                .setCreatedOn(LocalDateTime.now())
                .setComment(orderBindingDto.getComment() != null ? orderBindingDto.getComment() : "no comment")
                .setAddress(orderBindingDto.getAddress())
                .setContactPhoneNumber(orderBindingDto.getContactPhoneNumber())
                .setIsDelivered(false);

        this.orderRepository.saveAndFlush(order);
        user.getCart()
                .setProducts(new ArrayList<>())
                .setProductsSum(BigDecimal.ZERO);
    }

    @Transactional
    public List<OrderDetailsViewDto> getOrdersByUser(Principal principal) {
        User user = this.userService.getUserByUsername(principal.getName());
        return this.orderRepository
                .findAllOrdersByOwnerId(user.getId())
                .stream()
                .map(this::mapToOrderView)
                .collect(Collectors.toList());
    }

    public List<OrderDetailsViewDto> getAllOrders() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(this::mapToOrderView)
                .collect(Collectors.toList());
    }

    private OrderDetailsViewDto mapToOrderView(Order order) {
        OrderDetailsViewDto orderDetailsViewDto = this.modelMapper.map(order, OrderDetailsViewDto.class);
        orderDetailsViewDto.setClient(order.getOwner().getUsername());
        return orderDetailsViewDto;
    }

    public OrderDetailsViewDto getOrderById(Long id) {
        Order order = this.orderRepository.findOrderById(id);
        return mapToOrderView(order);
    }
}

package baykov.daniel.fooddelivery.repository;

import baykov.daniel.fooddelivery.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllOrdersByOwnerId(Long id);

    Order findOrderById(Long id);
}
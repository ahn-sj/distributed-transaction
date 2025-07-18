package tally.order.infastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tally.order.domain.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long orderId);

}

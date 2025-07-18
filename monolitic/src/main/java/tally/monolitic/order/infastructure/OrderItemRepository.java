package tally.monolitic.order.infastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tally.monolitic.order.domain.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long orderId);

}

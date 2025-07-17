package tally.monolitic.order.infastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tally.monolitic.order.domain.OrderIteam;

public interface OrderItemRepository extends JpaRepository<OrderIteam, Long> {
}

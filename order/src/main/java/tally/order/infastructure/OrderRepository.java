package tally.order.infastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tally.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

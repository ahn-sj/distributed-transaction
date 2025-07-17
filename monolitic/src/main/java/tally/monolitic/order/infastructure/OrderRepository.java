package tally.monolitic.order.infastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tally.monolitic.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

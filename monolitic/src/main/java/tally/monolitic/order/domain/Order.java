package tally.monolitic.order.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
        this.status = OrderStatus.CREATED;
    }

    public boolean isCompleted() {
        return this.status == OrderStatus.COMPLETED;
    }

    public void complete() {
        if (this.isCompleted()) {
            throw new RuntimeException("Order is already completed");
        }
        this.status = OrderStatus.COMPLETED;
    }

    private enum OrderStatus {
        CREATED,
        COMPLETED,
    }
}

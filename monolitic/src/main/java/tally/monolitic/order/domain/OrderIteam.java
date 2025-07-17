package tally.monolitic.order.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
public class OrderIteam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long productId;

    private Long quantity;

    public OrderIteam(final Long orderId, final Long productId, final Long quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}

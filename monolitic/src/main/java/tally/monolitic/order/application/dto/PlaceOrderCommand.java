package tally.monolitic.order.application.dto;

import java.util.List;

public record PlaceOrderCommand(
        List<OrderIteam> orderIteams
) {
    public record OrderIteam(
            Long productId,
            Long quantity
    ) {}
}

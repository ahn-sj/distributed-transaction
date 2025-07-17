package tally.monolitic.order.controller.dto;

import tally.monolitic.order.application.dto.PlaceOrderCommand;

import java.util.List;

public record PlaceOrderRequest (
        List<Orderiteam> orderIteams
) {
    public PlaceOrderCommand toPlaceOrderCommand() {
        return new PlaceOrderCommand(
                orderIteams
                        .stream()
                        .map((iteam -> new PlaceOrderCommand.OrderIteam(iteam.productId(),iteam.quantity())))
                        .toList()
        );
    }

    public record Orderiteam(
            Long productId,
            Long quantity
    ) {}
}

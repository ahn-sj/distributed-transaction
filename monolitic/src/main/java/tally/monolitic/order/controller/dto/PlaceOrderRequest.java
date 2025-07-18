package tally.monolitic.order.controller.dto;

import tally.monolitic.order.application.dto.PlaceOrderCommand;

public record PlaceOrderRequest (
        Long orderId
) {
    public PlaceOrderCommand toPlaceOrderCommand() {
        return new PlaceOrderCommand(orderId);
    }
}

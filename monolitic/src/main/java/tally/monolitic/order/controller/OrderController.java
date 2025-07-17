package tally.monolitic.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tally.monolitic.order.application.OrderService;
import tally.monolitic.order.controller.dto.PlaceOrderRequest;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/place")
    public void placeOrder(@RequestBody PlaceOrderRequest request) {
        orderService.placeOrder(request.toPlaceOrderCommand());
    }
}

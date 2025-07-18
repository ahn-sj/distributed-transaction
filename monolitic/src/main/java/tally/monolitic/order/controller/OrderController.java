package tally.monolitic.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tally.monolitic.order.application.OrderService;
import tally.monolitic.order.application.RedisLockService;
import tally.monolitic.order.application.dto.CreateOrderResult;
import tally.monolitic.order.controller.dto.CreateOrderRequest;
import tally.monolitic.order.controller.dto.CreateOrderResponse;
import tally.monolitic.order.controller.dto.PlaceOrderRequest;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final RedisLockService redisLockService;

    @PostMapping("/order")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        final CreateOrderResult order = orderService.createOrder(request.toCreateOrderCommand());

        return new CreateOrderResponse(order.orderId());
    }

    @PostMapping("/order/place")
    public void placeOrder(@RequestBody PlaceOrderRequest request) {
        String key = "order:monolitic:" + request.orderId();

        final boolean acquired = redisLockService.tryLock(key, request.orderId().toString());

        if(!acquired) {
            throw new RuntimeException("Order is already being processed: " + request.orderId());
        }

        try {
            orderService.placeOrder(request.toPlaceOrderCommand());
        } finally {
            redisLockService.releaseLock(key);
        }
    }
}

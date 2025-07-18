package tally.monolitic.order.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tally.monolitic.order.application.dto.CreateOrderCommand;
import tally.monolitic.order.application.dto.CreateOrderResult;
import tally.monolitic.order.application.dto.PlaceOrderCommand;
import tally.monolitic.order.domain.Order;
import tally.monolitic.order.domain.OrderItem;
import tally.monolitic.order.infastructure.OrderItemRepository;
import tally.monolitic.order.infastructure.OrderRepository;
import tally.monolitic.point.application.PointService;
import tally.monolitic.product.application.ProductService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PointService pointService;
    private final ProductService productService;

    @Transactional
    public CreateOrderResult createOrder(CreateOrderCommand command) {
        final Order order = orderRepository.save(new Order());
        final List<OrderItem> orderItems = command.orderItems()
                .stream()
                .map(item -> new OrderItem(order.getId(), item.productId(), item.quantity()))
                .toList();

        orderItemRepository.saveAll(orderItems);

        return new CreateOrderResult(order.getId());
    }

    @Transactional
    public void placeOrder(PlaceOrderCommand command) {
        final Order order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.isCompleted()) {
            return;
        }

        Long totalPrice = 0L;
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(order.getId());

        for (OrderItem item : orderItems) {
            final Long price = productService.buy(item.getProductId(), item.getQuantity());
            totalPrice += price;
        }

        pointService.use(1L, totalPrice);

        order.complete();
        orderRepository.save(order);

        log.info("결제 완료!");
        sleep(3000);
    }

    private static void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

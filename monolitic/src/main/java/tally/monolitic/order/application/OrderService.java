package tally.monolitic.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tally.monolitic.order.application.dto.PlaceOrderCommand;
import tally.monolitic.order.domain.Order;
import tally.monolitic.order.domain.OrderIteam;
import tally.monolitic.order.infastructure.OrderItemRepository;
import tally.monolitic.order.infastructure.OrderRepository;
import tally.monolitic.point.application.PointService;
import tally.monolitic.product.application.ProductService;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PointService pointService;
    private final ProductService productService;

    public void placeOrder(PlaceOrderCommand command) {
        final Order order = orderRepository.save(new Order());
        Long totalPrice = 0L;

        for (PlaceOrderCommand.OrderIteam item : command.orderIteams()) {
            orderItemRepository.save(new OrderIteam(order.getId(), item.productId(), item.quantity()));

            final Long price = productService.buy(item.productId(), item.quantity());
            totalPrice += price;
        }

        pointService.use(1L, totalPrice);
    }

}

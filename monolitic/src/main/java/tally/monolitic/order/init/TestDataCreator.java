package tally.monolitic.order.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tally.monolitic.point.domain.Point;
import tally.monolitic.point.infrastructure.PointRepository;
import tally.monolitic.product.domain.Product;
import tally.monolitic.product.infrastructure.ProductRepository;

@Component
@RequiredArgsConstructor
public class TestDataCreator {

    private final PointRepository pointRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    public void createTestData() {
        // 포인트 데이터 생성
        pointRepository.save(new Point(1L, 10_000L));

        // 제품 데이터 생성
        productRepository.save(new Product(100L, 100L));
        productRepository.save(new Product(100L, 200L));
    }
}

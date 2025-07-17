package tally.monolitic.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tally.monolitic.product.domain.Product;
import tally.monolitic.product.infrastructure.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long buy(Long productId, Long quantity) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        final Long totalPrice = product.calculatePrice(quantity);
        product.buy(quantity);

        productRepository.save(product);

        return totalPrice;
    }
}

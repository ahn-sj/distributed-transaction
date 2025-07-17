package tally.monolitic.product.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tally.monolitic.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

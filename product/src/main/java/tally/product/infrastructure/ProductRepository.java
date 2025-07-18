package tally.product.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tally.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

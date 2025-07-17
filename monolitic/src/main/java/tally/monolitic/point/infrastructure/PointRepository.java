package tally.monolitic.point.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tally.monolitic.point.domain.Point;

public interface PointRepository  extends JpaRepository<Point, Long> {

    Point findByUserId(Long userId);
}

package tally.monolitic.point.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tally.monolitic.point.domain.Point;
import tally.monolitic.point.infrastructure.PointRepository;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    @Transactional
    public void use(Long userId, Long amount) {
        final Point point = pointRepository.findByUserId(userId);

        if (point == null) {
            throw new RuntimeException("포인트가 없습니다 userId: " + userId);
        }

        point.use(amount);
        pointRepository.save(point);
    }
}

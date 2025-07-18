package tally.point.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "points")
@NoArgsConstructor
public class Point {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private Long userId;

    private Long amount;

    public Point(final Long userId, final Long amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public void use(final Long amount) {
        if (this.amount < amount) {
            throw new RuntimeException("Not enough points");
        }
        this.amount -= amount;
    }
}

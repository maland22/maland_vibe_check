package angi.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stars;

    @Column(length = 2000)
    private String comment;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


}

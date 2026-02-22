package angi.backend.entity;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(length = 2000)
    private String description;

    private String imageUrl;

    @ManyToMany(mappedBy = "artists")
    private List<Event> events = new ArrayList<>();
}

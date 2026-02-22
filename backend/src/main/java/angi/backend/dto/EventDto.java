package angi.backend.dto;

import angi.backend.entity.Event;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventDto {
    private Long id;
    private String title;
    private String location;
    private LocalDate eventDate;
    private String imageUrl;
    private List<String> artistNames;

    public static EventDto fromEntity(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .location(event.getLocation())
                .eventDate(event.getEventDate())
                .imageUrl(event.getImageUrl())
                .artistNames(event.getArtists()
                        .stream()
                        .map(a -> a.getFirstName() + " " + a.getLastName())
                        .collect(Collectors.toList()))
                .build();
    }
}

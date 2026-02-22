package angi.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;

}

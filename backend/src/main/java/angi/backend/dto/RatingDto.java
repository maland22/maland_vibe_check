package angi.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    public Long id;
    public int stars;
    public String comment;
    public LocalDateTime createdAt;
}

package angi.backend.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class JsonEvent {
    private String title;
    private String location;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    private String imageUrl;

    private List<String> artists;
    private List<JsonRating> ratings;
}

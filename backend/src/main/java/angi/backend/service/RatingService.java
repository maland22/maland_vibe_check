package angi.backend.service;

import angi.backend.dto.RatingDto;
import angi.backend.entity.Event;
import angi.backend.entity.Rating;
import angi.backend.repository.EventRepository;
import angi.backend.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final EventRepository eventRepository;

    public List<RatingDto> getRatingsByEvent(Long eventId) {

        return ratingRepository.findByEventId(eventId)
                .stream()
                .map(r -> new RatingDto(
                        r.getId(),
                        r.getStars(),
                        r.getComment(),
                        r.getCreatedAt()
                ))
                .toList();
    }

    public void addRating(Long eventId, RatingDto dto) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Rating rating = new Rating();
        rating.setStars(dto.getStars());
        rating.setComment(dto.getComment());
        rating.setCreatedAt(LocalDateTime.now());
        rating.setEvent(event);

        ratingRepository.save(rating);
    }
}

package angi.backend.controller;

import angi.backend.dto.RatingDto;
import angi.backend.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping("/event/{eventId}")
    public ResponseEntity< List<RatingDto>> getRatings(@PathVariable Long eventId) {
        return ResponseEntity.ok( ratingService.getRatingsByEvent(eventId));
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<Void> addRating(
            @PathVariable Long eventId,
            @RequestBody RatingDto dto) {

        ratingService.addRating(eventId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

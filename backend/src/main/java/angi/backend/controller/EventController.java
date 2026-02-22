package angi.backend.controller;

import angi.backend.dto.EventDto;
import angi.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity< Page<EventDto>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok( eventService.getAllEvents(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity< EventDto> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok( eventService.getEventById(id));
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity< Page<EventDto> > getByArtist(
            @PathVariable Long artistId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok( eventService.getEventsByArtist(artistId, PageRequest.of(page, size)));
    }
}

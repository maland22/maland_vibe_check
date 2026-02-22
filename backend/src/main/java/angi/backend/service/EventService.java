package angi.backend.service;

import angi.backend.dto.EventDto;
import angi.backend.entity.Event;
import angi.backend.entity.Rating;
import angi.backend.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Page<EventDto> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(EventDto::fromEntity);
    }

    public EventDto getEventById(Long id) {
        return EventDto.fromEntity(eventRepository.findById(id).orElseThrow());
    }

    public Page<EventDto> getEventsByArtist(Long artistId, Pageable pageable) {
        return eventRepository.findByArtistsId(artistId, pageable).map(EventDto::fromEntity);
    }
}

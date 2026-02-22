package angi.backend.config;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import angi.backend.entity.Artist;
import angi.backend.entity.Event;
import angi.backend.entity.Rating;
import angi.backend.repository.ArtistRepository;
import angi.backend.repository.EventRepository;
import angi.backend.repository.RatingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader  {

    private final ArtistRepository artistRepository;
    private final EventRepository eventRepository;
    private final RatingRepository ratingRepository;


    public void init(){
        createData();
    }

    public void createData(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try{

            InputStream artistStream = getClass().getResourceAsStream("/data/vibesArtists.json");
            if (artistStream != null) {
                List<JsonArtist> jsonArtists = objectMapper.readValue(artistStream, new TypeReference<List<JsonArtist>>() {});
                for (JsonArtist ja : jsonArtists) {
                    Artist artist = new Artist();
                    artist.setFirstName(ja.getFirstName());
                    artist.setLastName(ja.getLastName());
                    artist.setDescription(ja.getDescription());
                    artist.setImageUrl(ja.getImageUrl());
                    artistRepository.save(artist);
                }
                System.out.println("Artists geladen");
            } else {
                System.out.println("File vibes_Artists.json nicht gefunden!");
            }


            InputStream eventStream = getClass().getResourceAsStream("/data/vibesEvents.json");
            if (eventStream != null) {
                List<JsonEvent> jsonEvents = objectMapper.readValue(eventStream, new TypeReference<List<JsonEvent>>() {});
                for (JsonEvent je : jsonEvents) {
                    Event event = new Event();
                    event.setTitle(je.getTitle());
                    event.setLocation(je.getLocation());
                    event.setEventDate(je.getEventDate() != null ? je.getEventDate() : LocalDate.now());
                    event.setImageUrl(je.getImageUrl());


                    if (je.getArtists() != null) {
                        for (String artistName : je.getArtists()) {
                            String[] parts = artistName.split(" ");
                            if (parts.length >= 2) {
                                artistRepository.findAll().stream()
                                        .filter(a -> a.getFirstName().equals(parts[0]) && a.getLastName().equals(parts[1]))
                                        .findFirst()
                                        .ifPresent(event.getArtists()::add);
                            }
                        }
                    }


                    if (je.getRatings() != null) {
                        for (JsonRating jr : je.getRatings()) {
                            Rating rating = new Rating();
                            rating.setStars(jr.getStars());
                            rating.setComment(jr.getComment());
                            rating.setCreatedAt(jr.getCreatedAt() != null ? jr.getCreatedAt() : LocalDate.now().atStartOfDay());
                            rating.setEvent(event);
                            event.getRatings().add(rating);
                        }
                    }

                    eventRepository.save(event);
                }
                System.out.println("Events geladen");
            } else {
                System.out.println("File vibes_Event.json nicht gefunden");
            }
            System.out.println("DataLoader: Daten erfolgreich geladen!");
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
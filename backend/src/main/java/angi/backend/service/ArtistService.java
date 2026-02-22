package angi.backend.service;

import angi.backend.dto.ArtistDto;
import angi.backend.entity.Artist;
import angi.backend.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<ArtistDto> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public ArtistDto getArtistById(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        return convertToDto(artist);
    }


    public Page<ArtistDto> getAllPageable(Pageable pageable){
        Page<Artist> artists = artistRepository.findAll(pageable);
        return artists.map(this::convertToDto);
    }

    private ArtistDto convertToDto(Artist artist) {
        return new ArtistDto(
                artist.getId(),
                artist.getFirstName(),
                artist.getLastName(),
                artist.getDescription(),
                artist.getImageUrl()
        );
    }
}

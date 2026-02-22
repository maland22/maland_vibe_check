package angi.backend.controller;

import angi.backend.dto.ArtistDto;
import angi.backend.entity.Artist;
import angi.backend.service.ArtistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;



    @GetMapping
    public ResponseEntity< List<ArtistDto>> getAllArtists() {
        List<ArtistDto> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable Long id) {
        ArtistDto artists = artistService.getArtistById(id);
        return ResponseEntity.ok(artists);
    }

    //Testing

    @Transactional // will be needed to save into repository
    @GetMapping("/artists_pageable")
    public ResponseEntity< Page<ArtistDto>> getAllPageable(
            @RequestParam (defaultValue = "0") int page, //welche Seite
            @RequestParam (defaultValue = "10") int size, //weiviel pro seite
            @RequestParam (defaultValue = "id") String sortBy,
            @RequestParam (defaultValue = "asc") String sortDirection,
            Sort sort) {
        Sort sorter = sortDirection.equals("desc") ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ArtistDto> artists = artistService.getAllPageable(pageable);

        return ResponseEntity.ok(artists);
    }
}

package org.example.musicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.ArtistDto;
import org.example.musicservice.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping("add")
    public ResponseEntity<ArtistDto> addArtist(@RequestParam("name") String name) {
        ArtistDto saved = artistService.addArtist(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("add-member")
    public ResponseEntity<ArtistDto> addMember(@RequestParam("artist_name") String artistName,
                                               @RequestParam("member_name") String memberName) {
        return artistService.addMember(artistName, memberName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("search")
    public ResponseEntity<List<ArtistDto>> getArtist(@RequestParam String name) {
        return artistService.searchArtistsByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

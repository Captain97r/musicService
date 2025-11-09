package org.example.musicservice.controller;

import org.example.musicservice.dto.ReleaseDto;
import org.example.musicservice.service.ReleaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/release")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @PostMapping("add")
    public ResponseEntity<ReleaseDto> addRelease(@RequestParam("name") String title,
                                                 @RequestParam("artist") String artist,
                                                 @RequestParam("genre") String genre,
                                                 @RequestParam("releaseDate") String releaseData,
                                                 @RequestParam("releaseType") String releaseType) {

        return releaseService.addRelease(title, artist, genre, releaseData, releaseType)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("search-by-name")
    public ResponseEntity<List<ReleaseDto>> searchReleaseByName(@RequestParam("name") String title) {
        return releaseService.getReleasesByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("search-by-genre")
    public ResponseEntity<List<ReleaseDto>> searchReleaseByGenre(@RequestParam("genre") String genre) {
        return releaseService.getReleasesByGenre(genre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

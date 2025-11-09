package org.example.musicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.SongDto;
import org.example.musicservice.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @PostMapping("add")
    public ResponseEntity<SongDto> addSong(@RequestParam("title") String title,
                                           @RequestParam(value = "artist", required = false) String artist,
                                           @RequestParam(value = "release", required = false) String release) {
        return songService.addSong(title, artist, release)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("search-by-title")
    public ResponseEntity<List<SongDto>> searchSongsByTitle(@RequestParam("title") String title) {
        return songService.searchSongsByTitle(title)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("search-by-genre")
    public ResponseEntity<List<SongDto>> searchSongByGenre(@RequestParam("genre") String genre) {
        return songService.searchSongsByGenre(genre)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("search-by-artist")
    public ResponseEntity<List<SongDto>> searchSongByArtist(@RequestParam("artist") String artist) {
        return songService.searchSongsByArtist(artist)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("search-by-release")
    public ResponseEntity<List<SongDto>> searchSongByRelease(@RequestParam("release") String release) {
        return songService.searchSongsByRelease(release)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

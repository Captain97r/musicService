package org.example.musicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.PlaylistDto;
import org.example.musicservice.model.PlaylistMode;
import org.example.musicservice.service.PlaylistService;
import org.example.musicservice.utils.PlaylistCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping("generate-random")
    public ResponseEntity<PlaylistDto> getRandomPlaylist(@RequestParam Integer limit) {
        PlaylistCriteria criteria = new PlaylistCriteria();
        criteria.setMode(PlaylistMode.RANDOM);
        criteria.setMaxSongs(limit);

        return ResponseEntity.ok(new PlaylistDto(playlistService.generatePlaylist(criteria)));
    }

    @GetMapping("generate-by-genre")
    public ResponseEntity<PlaylistDto> getPlaylistByGenre(@RequestParam String genre,
                                                          @RequestParam Integer limit) {
        PlaylistCriteria criteria = new PlaylistCriteria();
        criteria.setMode(PlaylistMode.BY_GENRE);
        criteria.setGenre(genre);
        criteria.setMaxSongs(limit);

        return ResponseEntity.ok(new PlaylistDto(playlistService.generatePlaylist(criteria)));
    }

    @GetMapping("generate-by-year")
    public ResponseEntity<PlaylistDto> getPlaylistByDate(@RequestParam int minYear,
                                                         @RequestParam int maxYear,
                                                         @RequestParam Integer limit) {
        PlaylistCriteria criteria = new PlaylistCriteria();
        criteria.setMode(PlaylistMode.BY_RELEASE_DATE);
        criteria.setMinYear(minYear);
        criteria.setMaxYear(maxYear);
        criteria.setMaxSongs(limit);

        return ResponseEntity.ok(new PlaylistDto(playlistService.generatePlaylist(criteria)));
    }
}

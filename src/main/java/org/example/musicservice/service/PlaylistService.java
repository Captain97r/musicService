package org.example.musicservice.service;

import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.PlaylistDto;
import org.example.musicservice.dto.SongDto;
import org.example.musicservice.model.PlaylistMode;
import org.example.musicservice.model.Song;
import org.example.musicservice.repository.SongRepository;
import org.example.musicservice.utils.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlaylistService {
    private final Map<PlaylistMode, PlaylistStrategy> strategies;

    public PlaylistService(Map<String, PlaylistStrategy> strategies) {
        this.strategies = Map.of(
                PlaylistMode.RANDOM, strategies.get("randomPlaylistStrategy"),
                PlaylistMode.BY_GENRE, strategies.get("genrePlaylistStrategy"),
                PlaylistMode.BY_RELEASE_DATE, strategies.get("datePlaylistStrategy")
        );
    }


    public List<SongDto> generatePlaylist(PlaylistCriteria criteria) {
        PlaylistStrategy strategy = strategies.getOrDefault(
                criteria.getMode(),
                strategies.get("randomPlaylistStrategy")
        );
        return strategy.generate(criteria);
    }
}

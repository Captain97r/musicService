package org.example.musicservice.utils;

import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.SongDto;
import org.example.musicservice.model.Song;
import org.example.musicservice.repository.SongRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("randomPlaylistStrategy")
@RequiredArgsConstructor
public class RandomPlaylistStrategy implements PlaylistStrategy {

    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Override
    public List<SongDto> generate(PlaylistCriteria criteria) {
        Optional<List<Song>> songsOpt = Optional.of(songRepository.findAll());

        List<Song> songs = songsOpt.orElse(Collections.emptyList());
        if (songs.isEmpty()) {
            return Collections.emptyList();
        }
        Collections.shuffle(songs);

        int maxSongs = Math.min(criteria.getMaxSongs(), songs.size());

        return songs
                .stream()
                .limit(maxSongs)
                .map(songMapper::toDto)
                .collect(Collectors.toList());
    }
}

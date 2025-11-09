package org.example.musicservice.utils;

import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.SongDto;
import org.example.musicservice.model.Song;
import org.example.musicservice.repository.SongRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("datePlaylistStrategy")
@RequiredArgsConstructor
public class DatePlaylistStrategy implements PlaylistStrategy {

    private final SongRepository songRepository;
    private final SongMapper songMapper;


    @Override
    public List<SongDto> generate(PlaylistCriteria criteria) {
        LocalDate minDate = LocalDate.of(criteria.getMinYear(), 1, 1);
        LocalDate maxDate = LocalDate.of(criteria.getMaxYear(), 12, 31);
        Optional<List<Song>> songsOpt = songRepository.findSongsBetween(minDate, maxDate);

        List<Song> songs = songsOpt.orElse(Collections.emptyList());
        if (songs.isEmpty()) {
            return Collections.emptyList();
        }
        Collections.shuffle(songs);

        int maxSongs = Math.min(songs.size(), criteria.getMaxSongs());

        return songs
                .stream()
                .map(songMapper::toDto)
                .limit(maxSongs)
                .toList();
    }
}

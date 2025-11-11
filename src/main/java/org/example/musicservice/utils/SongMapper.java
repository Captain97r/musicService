package org.example.musicservice.utils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.SongDto;
import org.example.musicservice.model.Song;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongMapper {

    private final ArtistMapper artistMapper;
    private final ReleaseMapper releaseMapper;

    public SongDto toDto(Song song) {
        if (song == null) {
            return null;
        }
        return new SongDto(song.getTitle(), artistMapper.toDto(song.getArtist()), releaseMapper.toDto(song.getRelease()));
    }
}

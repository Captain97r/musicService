package org.example.musicservice.utils;

import org.example.musicservice.dto.SongDto;
import org.example.musicservice.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {
    public SongDto toDto(Song song) {
        return new SongDto(song.getTitle(), song.getArtist(), song.getRelease());
    }
}

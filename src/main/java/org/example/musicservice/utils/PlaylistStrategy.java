package org.example.musicservice.utils;

import org.example.musicservice.dto.SongDto;

import java.util.List;

public interface PlaylistStrategy {
    List<SongDto> generate(PlaylistCriteria criteria);
}

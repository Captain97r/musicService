package org.example.musicservice.dto;

import org.example.musicservice.model.Song;

import java.util.List;

public record PlaylistDto(
        List<SongDto> songs
) {}

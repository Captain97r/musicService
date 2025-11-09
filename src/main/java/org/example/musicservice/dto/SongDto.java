package org.example.musicservice.dto;

import org.example.musicservice.model.Artist;
import org.example.musicservice.model.Release;

public record SongDto(
        String title,
        Artist artist,
        Release release
) {}

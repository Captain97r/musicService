package org.example.musicservice.dto;

public record SongDto(
        String title,
        ArtistDto artist,
        ReleaseDto release
) {}

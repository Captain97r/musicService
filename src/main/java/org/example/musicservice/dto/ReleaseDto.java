package org.example.musicservice.dto;

public record ReleaseDto(
        String title,
        String artist,
        String genre,
        String releaseDate
) {}

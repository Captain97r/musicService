package org.example.musicservice.dto;

import java.util.List;

public record ArtistDto(
        String name,
        List<String> members
) {}

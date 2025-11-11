package org.example.musicservice.utils;

import org.example.musicservice.dto.ArtistDto;
import org.example.musicservice.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {
    public ArtistDto toDto(Artist entity) {
        if (entity == null) {
            return null;
        }
        return new ArtistDto(entity.getName(), entity.getMembers());
    }
}

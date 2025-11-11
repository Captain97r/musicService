package org.example.musicservice.utils;

import org.example.musicservice.dto.ReleaseDto;
import org.example.musicservice.model.Release;
import org.springframework.stereotype.Component;


@Component
public class ReleaseMapper {
    public ReleaseDto toDto(Release entity) {
        if (entity == null) {
            return null;
        }
        return new ReleaseDto(entity.getTitle(), entity.getArtist().getName(), entity.getGenre(), entity.getReleaseDate().toString());
    }
}

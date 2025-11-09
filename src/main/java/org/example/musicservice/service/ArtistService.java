package org.example.musicservice.service;

import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.ArtistDto;
import org.example.musicservice.model.Artist;
import org.example.musicservice.repository.ArtistRepository;
import org.example.musicservice.utils.ArtistMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public ArtistDto addArtist(String name) {
        Artist entity = new Artist();
        entity.setName(name);
        artistRepository.save(entity);
        return artistMapper.toDto(entity);
    }

    public Optional<ArtistDto> addMember(String artistName, String memberName) {
        Optional<Artist> artistOpt = artistRepository.getArtistByNameIgnoreCase(artistName);

        Artist artist = artistOpt.orElse(null);
        if (artist == null) {
            return Optional.empty();
        }

        artist.addMember(memberName);
        artistRepository.save(artist);
        return Optional.of(artistMapper.toDto(artist));
    }

    public Optional<List<ArtistDto>> searchArtistsByName(String name) {
        return artistRepository.getArtistsByNameContainingIgnoreCase(name)
                .map(artists -> artists.stream()
                        .map(artistMapper::toDto)
                        .toList());
    }
}

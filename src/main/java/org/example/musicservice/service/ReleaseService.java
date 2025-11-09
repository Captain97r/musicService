package org.example.musicservice.service;

import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.ReleaseDto;
import org.example.musicservice.model.Artist;
import org.example.musicservice.model.Release;
import org.example.musicservice.model.ReleaseType;
import org.example.musicservice.repository.ArtistRepository;
import org.example.musicservice.repository.ReleaseRepository;
import org.example.musicservice.utils.ReleaseMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReleaseService {

    private final ArtistRepository artistRepository;
    private final ReleaseRepository releaseRepository;
    private final ReleaseMapper releaseMapper;

    public Optional<ReleaseDto> addRelease(String title, String artistName, String genre, String releaseDate, String releaseType) {
        // We want exact match here
        Optional<Artist> artistOpt = artistRepository.getArtistByNameIgnoreCase(artistName);

        Artist artist = artistOpt.orElse(null);
        if (artist == null) {
            return Optional.empty();
        }

        Release release = new Release();
        release.setTitle(title);
        release.setGenre(genre);
        release.setReleaseDate(LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("d.M.yyyy")));

        switch(releaseType.toLowerCase()) {
            case "single":
                release.setType(ReleaseType.SINGLE);
                break;
            case "ep":
            case "extended_play":
            case "extended":
                release.setType(ReleaseType.EXTENDED_PLAY);
                break;
            default:
                release.setType(ReleaseType.ALBUM);
        }

        release.setArtist(artist);
        releaseRepository.save(release);
        return Optional.of(releaseMapper.toDto(release));
    }

    public Optional<List<ReleaseDto>> getReleasesByTitle(String title) {
        return releaseRepository.getReleasesByTitleContainingIgnoreCase(title)
                .map(releases -> releases.stream()
                        .map(releaseMapper::toDto)
                        .toList());
    }

    public Optional<List<ReleaseDto>> getReleasesByGenre(String genre) {
        return releaseRepository.getReleasesByGenreContainingIgnoreCase(genre)
                .map(releases -> releases.stream()
                        .map(releaseMapper::toDto)
                        .toList());
    }
}

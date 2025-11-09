package org.example.musicservice.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.musicservice.dto.SongDto;
import org.example.musicservice.model.Artist;
import org.example.musicservice.model.Release;
import org.example.musicservice.model.Song;
import org.example.musicservice.repository.ArtistRepository;
import org.example.musicservice.repository.ReleaseRepository;
import org.example.musicservice.repository.SongRepository;
import org.example.musicservice.utils.SongMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final ReleaseRepository releaseRepository;
    private final SongMapper songMapper;

    public Optional<SongDto> addSong(String title, String artistName, String releaseName) {
        Song song = new Song();
        song.setTitle(title);

        // We may have artist without release info (since there are some demos/unreleased songs), but not vice versa
        // since several artists may have equally named releases
        if (artistName == null) {
            return Optional.empty();
        }

        // We want exact match here
        Optional<Artist> artistOpt = artistRepository.getArtistByNameIgnoreCase(artistName);
        Artist artist = artistOpt.orElse(null);
        if (artist == null) {
            return Optional.empty();
        }
        song.setArtist(artist);

        if (releaseName == null) {
            songRepository.save(song);
            return Optional.of(songMapper.toDto(song));
        }

        // ... and here
        Optional<Release> releaseOpt = releaseRepository.getReleaseByTitleIgnoreCaseAndArtist(releaseName, artist);
        Release release = releaseOpt.orElse(null);
        if (release == null) {
            return Optional.empty();
        }

        song.setRelease(release);
        song.setArtist(release.getArtist());

        songRepository.save(song);
        return Optional.of(songMapper.toDto(song));
    }

    public Optional<List<SongDto>> searchSongsByTitle(String title) {
        return songRepository.getSongsByTitleContainingIgnoreCase(title)
                .map(songs -> songs.stream()
                        .map(songMapper::toDto)
                        .toList());
    }

    public Optional<List<SongDto>> searchSongsByGenre(String genre) {
        return songRepository.getSongsByRelease_GenreContainingIgnoreCase(genre)
                .map(songs -> songs.stream()
                        .map(songMapper::toDto)
                        .toList());
    }

    public Optional<List<SongDto>> searchSongsByArtist(String artist) {
        return songRepository.getSongsByArtist_NameContainingIgnoreCase(artist)
                .map(songs -> songs.stream()
                        .map(songMapper::toDto)
                        .toList());
    }

    public Optional<List<SongDto>> searchSongsByRelease(String release) {
        return songRepository.getSongsByRelease_TitleContainingIgnoreCase(release)
                .map(songs -> songs.stream()
                        .map(songMapper::toDto)
                        .toList());
    }
}

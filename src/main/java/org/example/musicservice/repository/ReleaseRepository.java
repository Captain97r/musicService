package org.example.musicservice.repository;

import org.example.musicservice.dto.ReleaseDto;
import org.example.musicservice.model.Artist;
import org.example.musicservice.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReleaseRepository extends JpaRepository<Release, Integer> {
    Optional<Release> getReleaseByTitleIgnoreCaseAndArtist(String title, Artist artist);

    Optional<List<Release>> getReleasesByTitleContainingIgnoreCase(String title);

    Optional<List<Release>> getReleasesByGenreContainingIgnoreCase(String genre);
}

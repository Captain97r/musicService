package org.example.musicservice.repository;

import org.example.musicservice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<List<Artist>> getArtistsByNameContainingIgnoreCase(String name);

    Optional<Artist> getArtistByNameIgnoreCase(String name);
}

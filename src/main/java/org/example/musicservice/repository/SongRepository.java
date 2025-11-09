package org.example.musicservice.repository;

import org.example.musicservice.dto.SongDto;
import org.example.musicservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    Optional<List<Song>> getSongsByTitleContainingIgnoreCase(String title);

    Optional<List<Song>> getSongsByRelease_GenreContainingIgnoreCase(String genre);

    Optional<List<Song>> getSongsByArtist_NameContainingIgnoreCase(String artist);

    Optional<List<Song>> getSongsByRelease_TitleContainingIgnoreCase(String release);

    Optional<List<Song>> findByRelease_ReleaseDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT s FROM Song s WHERE s.release.releaseDate BETWEEN :startDate AND :endDate")
    Optional<List<Song>> findSongsBetween(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);
}

package org.example.musicservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "releases")
@Getter @Setter @NoArgsConstructor
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private ReleaseType type;
    private String genre;
    private LocalDate releaseDate;

    @ManyToOne
    private Artist artist;
}

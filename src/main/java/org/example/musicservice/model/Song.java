package org.example.musicservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Table(name = "songs")
@Getter @Setter @NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Release release;

    public Artist getArtist() {
        if (release != null) {
            return release.getArtist();
        }
        else {
            return artist;
        }
    }
}

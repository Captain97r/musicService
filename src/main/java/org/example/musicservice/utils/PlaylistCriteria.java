package org.example.musicservice.utils;

import lombok.Getter;
import lombok.Setter;
import org.example.musicservice.model.PlaylistMode;

@Getter @Setter
public class PlaylistCriteria {
    private String artist;      // optional: prefer songs by or similar to this artist
    private String genre;           // optional
    private int maxSongs;           // max number of songs
    private int minYear;
    private int maxYear;
    private PlaylistMode mode;      // RANDOM, BALANCED, BY_RELEASE_DATE, etc.
    // + maybe mood or popularity fields
}

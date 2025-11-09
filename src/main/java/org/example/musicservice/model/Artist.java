package org.example.musicservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
@Getter @Setter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private List<String> members;

    public void addMember(String name) {
        if (members == null) {
            members = new ArrayList<>();
        }

        members.add(name);
    }
}

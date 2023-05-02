package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "t_song")
@Table(name = "t_song")
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "spotify_id")
    private String spotifyId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "song")
    private List<RouteSong> routeSongs;
}

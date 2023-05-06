package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "t_playlist")
@Table(name = "t_playlist")
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "spotify_id")
    private String spotifyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private AppUser owner;
}

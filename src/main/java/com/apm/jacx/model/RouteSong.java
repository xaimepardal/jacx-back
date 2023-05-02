package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "t_route_song")
@Table(name = "t_route_song")
@Data
public class RouteSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song")
    private Song song;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route")
    private Route route;
}

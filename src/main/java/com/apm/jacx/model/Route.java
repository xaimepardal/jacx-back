package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "t_route")
@Table(name = "t_route")
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "begin")
    private String begin;

    @Column(name = "finish")
    private String finish;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<AppUser> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<Playlist> playlists;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private AppUser owner;
}
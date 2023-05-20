package com.apm.jacx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id", unique = true)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "begin")
    private String begin;

    @Column(name = "finish")
    private String finish;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<WayPoint> wayPoints;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<AppUser> users;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Image> images;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Playlist> playlists;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AppUser appUser;
}
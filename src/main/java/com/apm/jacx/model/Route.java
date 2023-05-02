package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.geo.Point;

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

    @Column(name = "begin", columnDefinition = "geometry(Point, 4326)")
    private Point begin;

    @Column(name = "finish", columnDefinition = "geometry(Point, 4326)")
    private Point finish;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<UserRoute> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<Images> images;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private List<RouteSong> routeSongs;
}
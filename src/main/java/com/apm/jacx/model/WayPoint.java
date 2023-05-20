package com.apm.jacx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class WayPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waypoint_id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "orderPosition")
    private Integer orderPosition;

    @Column(name = "point")
    private String point;

    @Column(name = "url")
    private String url;

    @Column(name = "color")
    private String color;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "route_id")
    private Route route;
}
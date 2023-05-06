package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity(name = "t_images")
@Table(name = "t_images")
@Data
public class Image {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true)
        private Long id;

        @Column(name = "base64")
        private String base64;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "route")
        private Route route;

        @ManyToMany(fetch = FetchType.LAZY)
        private Set<AppUser> owner;
}

package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "t_images")
@Table(name = "t_images")
@Data
public class Images {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true)
        private Long id;

        @Column(name = "base64")
        private String base64;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "route")
        private Route route;

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
        private List<UserImage> userImage;
}

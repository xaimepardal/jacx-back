package com.apm.jacx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        @JoinColumn(name = "route")
        private Route route;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "owner")
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        private AppUser owner;
}

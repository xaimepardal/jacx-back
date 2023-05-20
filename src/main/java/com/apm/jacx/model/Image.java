package com.apm.jacx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Image {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "image_id", unique = true)
        private Long id;

        @Column(name = "base64")
        private String base64;

        @ToString.Exclude
        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        @JoinColumn(name = "route_id")
        private Route route;

        @ToString.Exclude
        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        @JoinColumn(name = "app_user_id")
        private AppUser appUser;
}

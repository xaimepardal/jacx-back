package com.apm.jacx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id", unique = true)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "user_token")
    private String userToken;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(unique = true)
    private String email;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<AppUser> friends;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Route> route;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Playlist> playlists;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Image> images;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Route> routesCreated;
}

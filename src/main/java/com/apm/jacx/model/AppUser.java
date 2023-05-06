package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity(name = "t_app_user")
@Table(name = "t_app_user")
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
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

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<AppUser> friends;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Route> route;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Playlist> playlists;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Route> routesCreated;
}

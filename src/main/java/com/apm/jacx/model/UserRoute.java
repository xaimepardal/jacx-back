package com.apm.jacx.model;

import jakarta.persistence.*;

@Entity(name = "t_user_route")
@Table(name = "t_user_route")
public class UserRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user")
    private AppUser appUser;
}

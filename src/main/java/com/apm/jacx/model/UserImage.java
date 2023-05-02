package com.apm.jacx.model;

import jakarta.persistence.*;

@Entity(name = "t_user_image")
@Table(name = "t_user_image")
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "um_user")
    private UMUser umUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image")
    private Images image;
}

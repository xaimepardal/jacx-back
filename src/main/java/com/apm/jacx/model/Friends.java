package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "t_friends")
@Table(name = "t_friends")
@Data
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user")
    private AppUser friend;

}

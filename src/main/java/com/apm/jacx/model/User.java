package com.apm.jacx.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usr_user")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String username;
}

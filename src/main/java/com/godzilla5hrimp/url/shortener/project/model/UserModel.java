package com.godzilla5hrimp.url.shortener.project.model;

import com.godzilla5hrimp.url.shortener.project.utils.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "user_entities")
@Getter
@Setter
public class UserModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: It is better to save passwords in another way
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private UserRole userRole;
}

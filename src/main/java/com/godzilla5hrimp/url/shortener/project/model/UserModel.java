package com.godzilla5hrimp.url.shortener.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//TODO: Have to add users to generate specifics links for them
@Entity
@Table(name = "user_entities")
@Getter
@Setter
public class UserModel {

    @Id
    @Column(name = "id")
    private Long id;

    //TODO: It is better to save passwords in another way
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;
}

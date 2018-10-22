package com.ferhat.multitenant.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Data
@Entity(name = "User_Entity")
public class User {

    @Id
    private String id;

    private String userName;

    private String userLastName;
}

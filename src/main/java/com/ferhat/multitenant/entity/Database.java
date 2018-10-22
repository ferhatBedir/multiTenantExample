package com.ferhat.multitenant.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
public class Database {

    @Id
    private String id;

    @NotNull
    private String databaseName;

    @NotNull
    private Boolean isActive;
}

package com.ferhat.multitenant.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAddModel {

    @NotNull
    private String userName;

    @NotNull
    private String userLastName;

    @NotNull
    private String databaseName;

}

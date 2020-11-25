package com.esprit.mypets.entity;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private TypeUser type;

    public User( ) {
    }

    public User(String name, String email, String password, TypeUser type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }
}

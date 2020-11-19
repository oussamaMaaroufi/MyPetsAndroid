package com.esprit.mypets.entity;

public class Acceuil {
    private String id;
    private User user;
    private Volontaires volontaires;
    private Animal animal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Volontaires getVolontaires() {
        return volontaires;
    }

    public void setVolontaires(Volontaires volontaires) {
        this.volontaires = volontaires;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}

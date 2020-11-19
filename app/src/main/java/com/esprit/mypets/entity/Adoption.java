package com.esprit.mypets.entity;

public class Adoption {
    private String id;
    private Abris abris;
    private Volontaires volontaires;
    private Animal animal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Abris getAbris() {
        return abris;
    }

    public void setAbris(Abris abris) {
        this.abris = abris;
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

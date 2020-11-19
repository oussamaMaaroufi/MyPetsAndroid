package com.esprit.mypets.entity;

import java.util.Date;

public class Urgence {
    private String id;
    private String Deceription;
    private Animal animal;
    private Veterinaires veterinaires;
    private Volontaires volontaires;
    private Date date;

    public Urgence() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeceription() {
        return Deceription;
    }

    public void setDeceription(String deceription) {
        Deceription = deceription;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Veterinaires getVeterinaires() {
        return veterinaires;
    }

    public void setVeterinaires(Veterinaires veterinaires) {
        this.veterinaires = veterinaires;
    }

    public Volontaires getVolontaires() {
        return volontaires;
    }

    public void setVolontaires(Volontaires volontaires) {
        this.volontaires = volontaires;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

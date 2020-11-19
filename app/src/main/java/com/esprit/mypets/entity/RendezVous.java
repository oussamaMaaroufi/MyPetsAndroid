package com.esprit.mypets.entity;

import java.util.Date;

public class RendezVous {
    private  String id;
    private  Date dateRv;
    private Veterinaires veterinaires;
    private Animal animal;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateRv() {
        return dateRv;
    }

    public void setDateRv(Date dateRv) {
        this.dateRv = dateRv;
    }
}

package com.esprit.mypets.entity;

public class LostAndFound {
    private String id;
    private Animal animal;
    private Volontaires volontaires;
    private String Description;
    private LostFound Type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Volontaires getVolontaires() {
        return volontaires;
    }

    public void setVolontaires(Volontaires volontaires) {
        this.volontaires = volontaires;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LostFound getType() {
        return Type;
    }

    public void setType(LostFound type) {
        Type = type;
    }
}

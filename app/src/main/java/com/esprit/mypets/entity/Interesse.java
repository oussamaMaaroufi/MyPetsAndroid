package com.esprit.mypets.entity;

public class Interesse {
    private  String id;
    private Animal animal1;
    private Animal animal2;
    private  boolean match;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Animal getAnimal1() {
        return animal1;
    }

    public void setAnimal1(Animal animal1) {
        this.animal1 = animal1;
    }

    public Animal getAnimal2() {
        return animal2;
    }

    public void setAnimal2(Animal animal2) {
        this.animal2 = animal2;
    }

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }
}

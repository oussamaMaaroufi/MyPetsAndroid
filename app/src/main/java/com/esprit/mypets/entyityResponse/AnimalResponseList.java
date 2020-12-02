package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Animal;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class AnimalResponseList {

    private String success;
    private String message;
    private ArrayList<Animal> animal = null;

    public ArrayList<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(ArrayList<Animal> animal) {
        this.animal = animal;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

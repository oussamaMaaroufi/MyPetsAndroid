package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Animal;

public class AnimalResponse {
    private String success;
    private String message;
    private Animal animal;


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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }



    @Override
    public String toString() {
        return "AnimalResponse{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", animal=" + animal +
                '}';
    }
}

package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.Veterinaires;

import java.util.ArrayList;

public class AbriResponseList {
    private String success;
    private String message;
    private ArrayList<Abris> abris;

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

    public ArrayList<Abris> getAbrises() {
        return abris;
    }

    public void setAbrises(ArrayList<Abris> abrises) {
        this.abris = abrises;
    }

    @Override
    public String toString() {
        return "VeterinairesResponse{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", abris=" + abris +
                '}';
    }
}

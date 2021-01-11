package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Adoption;
import com.esprit.mypets.entity.LostAndFound;

import java.util.ArrayList;

public class AdoptionResponseList {

    private String success;
    private String message;
    private ArrayList<Adoption> adoption;

    public AdoptionResponseList() {
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

    public ArrayList<Adoption> getAdoption() {
        return adoption;
    }

    public void setAdoption(ArrayList<Adoption> adoption) {
        this.adoption = adoption;
    }
}

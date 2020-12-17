package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Veterinaires;

import java.util.ArrayList;

public class VeterinaireResponseList {
    private String success;
    private String message;
    private ArrayList<Veterinaires> veterinaires;

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

    public ArrayList<Veterinaires> getVeterinaires() {
        return veterinaires;
    }

    public void setVeterinaires(ArrayList<Veterinaires> veterinaires) {
        this.veterinaires = veterinaires;
    }

    @Override
    public String toString() {
        return "VeterinairesResponse{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", veterinaires=" + veterinaires +
                '}';
    }
}

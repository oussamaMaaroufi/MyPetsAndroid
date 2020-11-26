package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Veterinaires;

public class VeterinairesResponse {
    private String success;
    private String message;
    private Veterinaires veterinaires;

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

    public Veterinaires getVeterinaires() {
        return veterinaires;
    }

    public void setVeterinaires(Veterinaires veterinaires) {
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

package com.esprit.mypets.entyityResponse;


import com.esprit.mypets.entity.Adoption;

public class AdoptionResponse {
    private String success;
    private String message;
    private Adoption adoption;


    public AdoptionResponse() {
    }

    public AdoptionResponse(String success, String message, Adoption adoption) {
        this.success = success;
        this.message = message;
        this.adoption = adoption;
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

    public Adoption getAdoption() {
        return adoption;
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }
}

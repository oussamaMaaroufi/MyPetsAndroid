package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Volontaires;

public class VolontairesResponse {
    private String success;
    private String message;
    private Volontaires volontaires;

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

    public Volontaires getVolontaires() {
        return volontaires;
    }

    public void setVolontaires(Volontaires volontaires) {
        this.volontaires = volontaires;
    }

    @Override
    public String toString() {
        return "VolontairesResponse{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", volontaires=" + volontaires +
                '}';
    }
}

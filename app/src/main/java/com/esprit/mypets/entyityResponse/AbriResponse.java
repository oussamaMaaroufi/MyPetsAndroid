package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Abris;

public class AbriResponse {
    private String success;
    private String message;
    private Abris abris;

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

    public Abris getAbris() {
        return abris;
    }

    public void setAbris(Abris abris) {
        this.abris = abris;
    }

    @Override
    public String toString() {
        return "AbriResponse{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", abris=" + abris +
                '}';
    }
}

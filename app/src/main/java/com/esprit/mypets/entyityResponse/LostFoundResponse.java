package com.esprit.mypets.entyityResponse;


import com.esprit.mypets.entity.LostAndFound;

public class LostFoundResponse {
    private String success;
    private String message;
    private LostAndFound LostAndFound;

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

    public LostAndFound getLostAndFound() {
        return LostAndFound;
    }

    public void setLostAndFound(LostAndFound lostAndFound) {
        LostAndFound = lostAndFound;
    }

    @Override
    public String toString() {
        return "LostFoundResponse{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", LostAndFound=" + LostAndFound +
                '}';
    }
}

package com.esprit.mypets.entyityResponse;


import com.esprit.mypets.entity.LostAndFound;

import java.util.ArrayList;

public class LostFoundResponseList {
    private String success;
    private String message;
    private ArrayList<LostAndFound> lostAndFound;

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

    public ArrayList<LostAndFound> getLostAndFound() {
        return lostAndFound;
    }

    public void setLostAndFound(ArrayList<LostAndFound> lostAndFound) {
        this.lostAndFound = lostAndFound;
    }
}

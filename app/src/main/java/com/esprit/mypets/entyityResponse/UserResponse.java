package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.User;

public class UserResponse {
    private String success;
    private String message;
    private User user;
    private String token;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}

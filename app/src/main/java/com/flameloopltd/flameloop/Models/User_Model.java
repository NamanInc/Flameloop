package com.flameloopltd.flameloop.Models;

public class User_Model {
    
    private String USER_FULL_NAME;
    private String USER_EMAIL;
    private String USER_INFO;



    public User_Model() {
    }

    public User_Model(String USER_FULL_NAME, String USER_EMAIL, String USER_INFO) {
        this.USER_FULL_NAME = USER_FULL_NAME;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_INFO = USER_INFO;
    }

    public String getUSER_FULL_NAME() {
        return USER_FULL_NAME;
    }

    public void setUSER_FULL_NAME(String USER_FULL_NAME) {
        this.USER_FULL_NAME = USER_FULL_NAME;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getUSER_INFO() {
        return USER_INFO;
    }

    public void setUSER_INFO(String USER_INFO) {
        this.USER_INFO = USER_INFO;
    }
}

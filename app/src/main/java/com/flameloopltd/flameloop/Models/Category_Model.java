package com.flameloopltd.flameloop.Models;

public class Category_Model {

    public String CATEGORY_ID;
    public String CATEGORY_PICTURE;
    public String CATEGORY_TEXT;

    public Category_Model() {
    }

    public Category_Model(String CATEGORY_ID, String CATEGORY_PICTURE, String CATEGORY_TEXT) {
        this.CATEGORY_ID = CATEGORY_ID;
        this.CATEGORY_PICTURE = CATEGORY_PICTURE;
        this.CATEGORY_TEXT = CATEGORY_TEXT;
    }

    public String getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(String CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getCATEGORY_PICTURE() {
        return CATEGORY_PICTURE;
    }

    public void setCATEGORY_PICTURE(String CATEGORY_PICTURE) {
        this.CATEGORY_PICTURE = CATEGORY_PICTURE;
    }

    public String getCATEGORY_TEXT() {
        return CATEGORY_TEXT;
    }

    public void setCATEGORY_TEXT(String CATEGORY_TEXT) {
        this.CATEGORY_TEXT = CATEGORY_TEXT;
    }
}


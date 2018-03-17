package com.group.uni.sojoo2018.activities.telaNoticias;

import java.util.ArrayList;

public class Noticias {

    private String title;

    private String message;

    private int image;


    public Noticias(String title, String message, int image) {
        this.title = title;
        this.message = message;
        this.image = image;
    }

    public Noticias() {

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP;

import java.util.ArrayList;

public class ShowsAZ {

    private String title;

    private String message;

    private ArrayList<ShowsAZ> singleItemModelArrayList;

    int foto;

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }


    public ShowsAZ(String title, String message, int foto) {
        this.title = title;
        this.message = message;
        this.foto = foto;
    }

    public ShowsAZ(String title, String message, int foto, ArrayList<ShowsAZ> singleItemModelArrayList) {
        this.title = title;
        this.message = message;
        this.singleItemModelArrayList = singleItemModelArrayList;
        this.foto = foto;
    }


    public ShowsAZ() {

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

    public ArrayList<ShowsAZ> getSingleItemArrayList() {
        return singleItemModelArrayList;
    }

    public void setSingleItemArrayList(ArrayList<ShowsAZ> singleItemModelArrayList) {
        this.singleItemModelArrayList = singleItemModelArrayList;
    }
}

package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP;

import java.io.Serializable;
import java.util.ArrayList;

public class Shows  implements Serializable {

    private ArrayList<Shows> singleItemModelArrayList;

    private String title;

    int foto;

    int dia;

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    private String message;


    public Shows(String title, String message, int foto) {
        this.title = title;
        this.message = message;
        this.foto = foto;
    }

    public Shows(String title, String message, int foto, ArrayList<Shows> singleItemModelArrayList) {
        this.title = title;
        this.message = message;
        this.foto = foto;
        this.singleItemModelArrayList = singleItemModelArrayList;
    }

    public Shows(String title, String message, int foto, int dia) {
        this.title = title;
        this.message = message;
        this.foto = foto;
        this.singleItemModelArrayList = singleItemModelArrayList;
        this.dia = dia;
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

    public ArrayList<Shows> getSingleItemArrayList() {
        return singleItemModelArrayList;
    }

    public void setSingleItemArrayList(ArrayList<Shows> singleItemModelArrayList) {
        this.singleItemModelArrayList = singleItemModelArrayList;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}

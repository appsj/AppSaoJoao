package com.group.uni.sojoo2018.activities.telaAtracoes;

import java.util.ArrayList;

public class Eventos {

    private int imagem;

    private String title;

    private String message;

    public Eventos() {

    }

    public Eventos(int imagem, String title, String message) {
        this.imagem = imagem;
        this.title = title;
        this.message = message;
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

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}

package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP;

public class ModeloCalendario {

    private String title;

    private String message;

    private int tipo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public ModeloCalendario(String title, String message, int tipo) {
        this.title = title;
        this.message = message;
        this.tipo = tipo;
    }

    public ModeloCalendario() {

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

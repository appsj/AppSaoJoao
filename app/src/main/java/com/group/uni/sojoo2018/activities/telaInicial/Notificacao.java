package com.group.uni.sojoo2018.activities.telaInicial;


public class Notificacao {
    private String titulo;
    private String desc;
    private int imagem;
    private int acao;



    public Notificacao(String titulo, String desc, int imagem, int acao) {
        this.titulo = titulo;
        this.desc = desc;
        this.imagem = imagem;
        this.acao = acao;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesc() {
        return desc;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAcao() {
        return acao;
    }

    public void setAcao(int acao) {
        this.acao = acao;
    }
}
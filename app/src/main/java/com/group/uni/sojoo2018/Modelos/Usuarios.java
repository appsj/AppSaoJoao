package com.group.uni.sojoo2018.Modelos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.group.uni.sojoo2018.ConfigFirebase.ConfigFirebase;

public class Usuarios {

    private String id, email, senha;
    private int diasQueFoi;

    public Usuarios(){

    }

    public void salvarUsuario(){
        DatabaseReference referenciaDatabase = ConfigFirebase.getFirebase();
        referenciaDatabase.child("usuarios").child(this.getId()).setValue(this);
    }
    @Exclude // nao salvar duplicado no firebase
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Exclude // não salvar duplicada no firebase
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getDiasQueFoi() {
        return diasQueFoi;
    }

    public void setDiasQueFoi(int diasQueFoi) {
        this.diasQueFoi = diasQueFoi;
    }
}

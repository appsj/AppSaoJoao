package com.group.uni.sojoo2018.ConfigFirebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;

    public static DatabaseReference getFirebase(){ // pega referencia do DataBase
        if(referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }
    public static FirebaseAuth getFirebaseAutenticacao(){ // pega referencia para usuario
        if(autenticacao==null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

}

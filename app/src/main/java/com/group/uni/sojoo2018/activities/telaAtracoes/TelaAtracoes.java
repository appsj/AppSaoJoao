package com.group.uni.sojoo2018.activities.telaAtracoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.TelaShowsPP;
import com.group.uni.sojoo2018.utils.Tema;

public class TelaAtracoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atracoes);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  //Criar só uma toolbar
        Tema.tema(toolbar, this);
        setSupportActionBar(toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
            toolbarText.setText("Atrações");
            setSupportActionBar(toolbar);

        //<editor-fold desc="Temp">

        //</editor-fold>

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Ativa o botão de voltar

        ImageView pp = findViewById(R.id.pp);

        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaAtracoes.this, TelaShowsPP.class));
            }
        });
    }



}

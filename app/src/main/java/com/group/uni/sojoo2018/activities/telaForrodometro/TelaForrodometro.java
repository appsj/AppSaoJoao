package com.group.uni.sojoo2018.activities.telaForrodometro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.anastr.speedviewlib.SpeedView;
import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.utils.Tema;

public class TelaForrodometro extends AppCompatActivity {

    SpeedView speedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_forrodometro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  //Criar só uma toolbar
        Tema.tema(toolbar, this);
        setSupportActionBar(toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
        toolbarText.setText("Forrodômetro");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        speedView = findViewById(R.id.speedView2);

        speedView.speedTo(30);
    }

}

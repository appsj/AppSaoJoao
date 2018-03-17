package com.group.uni.sojoo2018.activities.telaSobre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.utils.Tema;

import java.util.Random;

public class TelaSobre extends AppCompatActivity {

    RelativeLayout layout;
    GridView grid;
    String[] web = {
            //<editor-fold desc="Nome dos Desenvolvedores">
            "Fábio Júnior",
            "Larissa Amorim",
            "Gleisson Michael",
            "Arthur Stevam"
            //</editor-fold>
    } ;
    int[] imageId = {
            //<editor-fold desc="Fotos dos Desenvolvedores">
            R.drawable.fabio,
            R.drawable.larissa,
            R.drawable.gleisson,
            R.drawable.arthur
            //</editor-fold>
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Tema.tema(toolbar, this);


        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
        toolbarText.setText("Sobre");

        setSupportActionBar(toolbar);
        layout = findViewById(R.id.relativeLayout2);
        mudarCorBackground();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Ativa o botão voltar da Toolbar

        //<editor-fold desc="Grade de Desenvolvedores">
        EquipeGrid adapter = new EquipeGrid(TelaSobre.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid2);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               // Toast.makeText(TelaSobre.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        //</editor-fold>
    }

    @Override
    public void onResume(){
        super.onResume();
        mudarCorBackground();

    }

    public void mudarCorBackground() {


        Random gerador = new Random(); //Gerador de caracteres aleatórios

        int cor = gerador.nextInt(4); //Gera um inteiro entre 0 e 4

        switch (cor) {
            case 0:
                layout.setBackground(getResources().getDrawable(R.drawable.shape_azul)); //Seta uma das imagens como background
                break;
            case 1:
                layout.setBackground(getResources().getDrawable(R.drawable.shape_azul));
                break;
            case 2:
                layout.setBackground(getResources().getDrawable(R.drawable.shape_laranja));
                break;
            case 3:
                layout.setBackground(getResources().getDrawable(R.drawable.shape_preto));
                break;
            case 4:
                layout.setBackground(getResources().getDrawable(R.drawable.shape_rosa));
                break;
        }


    }

}

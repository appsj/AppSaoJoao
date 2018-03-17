package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.detalhes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.Shows;

public class TelaDetalhesPPNAOUSADO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhes_pp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Shows show = (Shows) intent.getSerializableExtra("show");

        ImageView bg_show = findViewById(R.id.bg_show);
        bg_show.setBackgroundResource(show.getFoto());


    }

}

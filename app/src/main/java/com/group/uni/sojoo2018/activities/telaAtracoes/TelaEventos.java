package com.group.uni.sojoo2018.activities.telaAtracoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import android.support.v4.widget.SwipeRefreshLayout;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.TelaShowsPP;
import com.group.uni.sojoo2018.utils.Tema;

import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.support.v7.widget.Toolbar;


public class TelaEventos extends AppCompatActivity {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;
    private TextView toolbarText;
    private Toolbar toolbar;

    // @BindView(R.id.swipe_refresh_recycler_list)
    // SwipeRefreshLayout swipeRefreshRecyclerList;

    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private EventosAdapter mAdapter;

    private ArrayList<Eventos> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_eventos);

        // ButterKnife.bind(this);
        findViews();
        initToolbar("Eventos");
        setAdapter();

        swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Do your stuff on refresh
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (swipeRefreshRecyclerList.isRefreshing())
                            swipeRefreshRecyclerList.setRefreshing(false);
                    }
                }, 5000);

            }
        });

    }

    private void findViews() {

        toolbarText = (TextView) findViewById(R.id.toolbar_text);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
    }

    public void initToolbar(String title) {

        if (toolbarText != null && toolbar != null) {
            toolbarText.setText("Eventos");
            toolbarText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            toolbarText.setPadding(0, 0, 100, 0);
        }
        Tema.tema(toolbar, this); //Ajustar o tamanho da Toolbat
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
    }


    private void setAdapter() {


        modelList.add(new Eventos( R.drawable.pp, "Parque do Povo", "O evento mais movimentado dessa festa"));
        modelList.add(new Eventos( R.drawable.sitiosj,"Sítio São João", "Venha curtir"));
        modelList.add(new Eventos( R.drawable.vila,"Vila Forró", "Venha curtir"));
        modelList.add(new Eventos( R.drawable.spazzio,"Spazzio", "Venha curtir"));


        mAdapter = new EventosAdapter(TelaEventos.this, modelList);

        recyclerView.setHasFixedSize(true);


        final GridLayoutManager layoutManager = new GridLayoutManager(TelaEventos.this, 2);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new EventosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Eventos model) {

                switch (position){
                    case 0:
                        startActivity(new Intent(TelaEventos.this, TelaShowsPP.class));
                }

                //handle item click events here
                Toast.makeText(TelaEventos.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();


            }
        });


    }


}

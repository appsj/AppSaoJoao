package com.group.uni.sojoo2018.activities.telaAtracoes.atracoesParqueDoPovoNAOUSADO;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.utils.Tema;

import java.util.ArrayList;
import java.util.List;

public class TelaAtracoesPP extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter2;
    private List<Data> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atracoes_pp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Tema.tema(toolbar, this);
        setSupportActionBar(toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
            toolbarText.setText("Parque do Povo");
            setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Ativa o botão voltar





        //<editor-fold desc="Calendário">
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);


        albumList = new ArrayList<>();
        adapter2 = new DataAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new TelaAtracoesPP.GridSpacingItemDecoration(1, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter2);


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        prepareAlbums();
        //</editor-fold>

    }

    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {

        Data a = new Data("Domingo", "02", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Segunda", "03", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Segunda", "04", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Terça", "05", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Quarta", "06", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Quinta", "07", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Sexta", "08", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Sábado", "09", "Jun", "20", "00");
        albumList.add(a);


        a = new Data("Domingo", "10", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Segunda", "11", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Segunda", "12", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Terça", "13", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Quarta", "14", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Quinta", "15", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Sexta", "16", "Jun", "20", "00");
        albumList.add(a);

        a = new Data("Sábado", "17", "Jun", "20", "00");
        albumList.add(a);
        adapter2.notifyDataSetChanged();
    }
}

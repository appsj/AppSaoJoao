package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.support.v4.widget.SwipeRefreshLayout;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import android.support.v7.widget.Toolbar;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaAtracoes.atracoesParqueDoPovoNAOUSADO.TelaAtracoesPP;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.detalhes.CardsDetalhes;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.detalhes.CardsDetalhesAdapter;
import com.group.uni.sojoo2018.activities.telaInicial.Notificacao;
import com.group.uni.sojoo2018.activities.telaNoticias.Noticias;
import com.group.uni.sojoo2018.utils.Tema;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;


public class TelaShowsPP extends AppCompatActivity {

    int positionAnterior;
    CardView cardview_calendar;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterShow mAdapter_Shows;

    private ArrayList<Shows> show = new ArrayList<>();

    private RecyclerView recyclerView_Shows;
    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;
    private Toolbar toolbar;

    // @BindView(R.id.swipe_refresh_recycler_list)
    // SwipeRefreshLayout swipeRefreshRecyclerList;

    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private RecyclerViewAdapter mAdapter;
    private RecyclerViewScrollListener scrollListener;

    private ArrayList<ModeloCalendario> modelList = new ArrayList<>();


    private ProgressBar progressBar;
    private CardStackView cardStackView;
    private CardsDetalhesAdapter adapter;
    List<Shows> spots = new ArrayList<>();
    CardsDetalhes shows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telapp);

        // ButterKnife.bind(this);
        findViews();
        criarAtracoes();
        recyclerView_Shows = (RecyclerView) findViewById(R.id.recycler_view_shows);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Tema.tema(toolbar, this);
        setSupportActionBar(toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
        toolbarText.setText("Parque do Povo");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Ativa o botão voltar

        setAdapterCalendario();
        setAdapter_Shows(0);

        adapter = new CardsDetalhesAdapter(getApplicationContext());
        setupCardsDetalhes();
        reloadCardsDetalhes();


        swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { //Puxar tela para atualizar
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
    @Override
    public void onBackPressed() {

        if(adapter.isEmpty()){
            super.onBackPressed();
        } else {
            adapter.clear();
        }
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
        cardview_calendar = findViewById(R.id.cardview_calendar);
    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);


        getMenuInflater().inflate(R.menu.menu_search, menu);

//
//        // Retrieve the SearchView and plug it into SearchManager
//        final SearchView searchView = (SearchView) MenuItemCompat
//                .getActionView(menu.findItem(R.id.action_search));
//
//        SearchManager searchManager = (SearchManager) this.getSystemService(this.SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
//
//        //changing edittext color
//        EditText searchEdit = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
//        searchEdit.setTextColor(getResources().getColor(R.color.titulo));
//        searchEdit.setHintTextColor(getResources().getColor(R.color.titulo));
//        searchEdit.setBackgroundColor(Color.TRANSPARENT);
//        searchEdit.setHint("Procurar");
//
//        InputFilter[] fArray = new InputFilter[2];
//        fArray[0] = new InputFilter.LengthFilter(40);
//        fArray[1] = new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//
//                for (int i = start; i < end; i++) {
//
//                    if (!Character.isLetterOrDigit(source.charAt(i)))
//                        return "";
//                }
//
//
//                return null;
//
//
//            }
//        };
//        searchEdit.setFilters(fArray);
//        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
//        v.setBackgroundColor(Color.TRANSPARENT);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                ArrayList<Shows> filterList = new ArrayList<Shows>();
//                if (s.length() > 0) {
//                    for (int i = 0; i <show.size(); i++) {
//                        if (show.get(i).getTitle().toLowerCase().contains(s.toString().toLowerCase())) {
//                            filterList.add(show.get(i));
//                            mAdapter_Shows.updateList(filterList);
//                        }
//                    }
//
//                } else {
//                    mAdapter_Shows.updateList(show);
//                }
//                return false;
//            }
//        });
//
//
        return true;
    }

    private void setAdapterCalendario() {


        modelList.add(new ModeloCalendario("Domingo", "30", 2));

        modelList.add(new ModeloCalendario("Segunda", "31", 1));
        modelList.add(new ModeloCalendario("Terça", "01", 3));
        modelList.add(new ModeloCalendario("Quarta", "02", 1));
        modelList.add(new ModeloCalendario("Quinta", "03", 1));
        modelList.add(new ModeloCalendario("Sexta", "04", 1));
        modelList.add(new ModeloCalendario("Sábado", "05", 2));
        modelList.add(new ModeloCalendario("Domingo", "06", 2));
        modelList.add(new ModeloCalendario("Segunda", "07", 1));
        modelList.add(new ModeloCalendario("Terça", "08", 1));
        modelList.add(new ModeloCalendario("Quarta", "09", 1));
        modelList.add(new ModeloCalendario("Quinta", "10", 1));
        modelList.add(new ModeloCalendario("Sexta", "11", 1));
        modelList.add(new ModeloCalendario("Sábado", "12", 2));

        mAdapter = new RecyclerViewAdapter(TelaShowsPP.this, modelList);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new TelaAtracoesPP.GridSpacingItemDecoration(1, dpToPx(0), true));
        // use a linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        // recyclerView.findViewHolderForAdapterPosition(3).itemView.performClick();
        scrollListener = new RecyclerViewScrollListener() {

            public void onEndOfScrollReached(RecyclerView rv) {

                //Toast.makeText(TelaShowsPP.this, "End of the RecyclerView reached. Do your pagination stuff here", Toast.LENGTH_SHORT).show();

                scrollListener.disableScrollListener();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
          /*
             Note: The below two methods should be used wisely to handle the pagination enable and disable states based on the use case.
                     1. scrollListener.disableScrollListener(); - Should be called to disable the scroll state.
                     2. scrollListener.enableScrollListener(); - Should be called to enable the scroll state.
          */


        mAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ModeloCalendario model) {
                //mAdapter.updateList(modelList);
                //handle item click events here
                //Toast.makeText(TelaShowsPP.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
                //modelList.removeAll(modelList);
                //setAdapterCalendario();
                cardview_calendar = view.findViewById(R.id.cardview_calendar);


                TextView a = findViewById(R.id.frase);
                a.setText(model.getTitle()+", "+model.getMessage());
                cardview_calendar.setElevation(20);
                show.removeAll(show);
                setAdapter_Shows(position);


            }
        });


    }

    ArrayList<Shows> showsbd;
    public void criarAtracoes(){

        showsbd = new ArrayList<>();

        showsbd.add(new Shows("Wesley Safadão", "Diferente não, estranho!", R.drawable.safadao, 1));
        showsbd.add(new Shows("Xand Avião", "Faz o x aí!", R.drawable.xand, 1));
        showsbd.add(new Shows("Mano Walter", "Ela me feeeeez, comprar um carroooo...", R.drawable.manowalter, 1));
        showsbd.add(new Shows("Banda A Loba", "Tem que respeitar!", R.drawable.loba, 2));
        showsbd.add(new Shows("Mastruz com Leite", "Essa é das antigas", R.drawable.mastruz, 2));
        showsbd.add(new Shows("Elba Ramalho", "Hello " + " Android", R.drawable.elba, 2));
        showsbd.add(new Shows("Os Três do Nordeste", "Hello " + " Beta", R.drawable.tresdonordeste, 3));
        showsbd.add(new Shows("Wesley Safadão", "Hello " + " Android", R.drawable.safadao, 3));
        showsbd.add(new Shows("Elba Ramalho", "Hello " + " Beta", R.drawable.elba, 4));
        showsbd.add(new Shows("Xand Avião", "Hello " + " Android", R.drawable.xand, 4));
        showsbd.add(new Shows("Elba Ramalho", "Hello " + " Beta", R.drawable.elba, 4));


        showsbd.add(new Shows("Wesley Safadão", "Diferente não, estranho!", R.drawable.safadao, 5));
        showsbd.add(new Shows("Xand Avião", "Faz o x aí!", R.drawable.xand, 5));
        showsbd.add(new Shows("Mano Walter", "Ela me feeeeez, comprar um carroooo...", R.drawable.manowalter, 5));
        showsbd.add(new Shows("Banda A Loba", "Tem que respeitar!", R.drawable.loba, 5));
        showsbd.add(new Shows("Mastruz com Leite", "Essa é das antigas", R.drawable.mastruz, 6));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!" , R.drawable.elba, 6));
        showsbd.add(new Shows("Três do Nordeste", "Vem curtir!", R.drawable.tresdonordeste, 7));
        showsbd.add(new Shows("Wesley Safadão", "Vem curtir!", R.drawable.safadao, 7));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 8));
        showsbd.add(new Shows("Xand Avião", "Vem curtir!", R.drawable.xand, 8));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));
        showsbd.add(new Shows("Elba Ramalho", "Vem curtir!", R.drawable.elba, 9));


        showsbd.add(new Shows("Wesley Safadão", "Diferente não, estranho!", R.drawable.safadao, 10));
        showsbd.add(new Shows("Xand Avião", "Faz o x aí!", R.drawable.xand, 10));
        showsbd.add(new Shows("Mano Walter", "Ela me feeeeez, comprar um carroooo...", R.drawable.manowalter, 10));
        showsbd.add(new Shows("Banda A Loba", "Tem que respeitar!", R.drawable.loba, 11));
        showsbd.add(new Shows("Mastruz com Leite", "Essa é das antigas", R.drawable.mastruz, 12));
        showsbd.add(new Shows("sasa", "Hello " + " Android", R.drawable.elba, 12));
        showsbd.add(new Shows("Betgfhyjta", "Hello " + " Beta", R.drawable.tresdonordeste, 13));
        showsbd.add(new Shows("Andrsasaoid", "Hello " + " Android", R.drawable.safadao, 13));
        showsbd.add(new Shows("Betsdffdfda", "Hello " + " Beta", R.drawable.elba, 14));
        showsbd.add(new Shows("Andsasasroid", "Hello " + " Android", R.drawable.xand, 14));
        showsbd.add(new Shows("Bsaseta", "Hello " + " Beta", R.drawable.elba, 14));


    }


    private void setAdapter_Shows(int position) {



        switch (position+1) {
            case 1:
                for(Shows showa : showsbd){
                    if(showa.dia == 1) {
                        show.add(showa);
                    }
                }
                break;
            case 2:
                for(Shows showa : showsbd){
                    if(showa.dia == 2) {
                        show.add(showa);
                    }
                }
                break;
            case 3:
                for(Shows showa : showsbd){
                    if(showa.dia == 3) {
                        show.add(showa);
                    }
                }
                break;
            case 4:
                for(Shows showa : showsbd){
                    if(showa.dia == 4) {
                        show.add(showa);
                    }
                }
                break;
            case 5:
                for(Shows showa : showsbd){
                    if(showa.dia == 5) {
                        show.add(showa);
                    }
                }
                break;
            case 6:
                for(Shows showa : showsbd){
                    if(showa.dia == 6) {
                        show.add(showa);
                    }
                }
                break;
            case 7:
                for(Shows showa : showsbd){
                    if(showa.dia == 7) {
                        show.add(showa);
                    }
                }
                break;
            case 8:
                for(Shows showa : showsbd){
                    if(showa.dia == 8) {
                        show.add(showa);
                    }
                }
                break;
            case 9:
                for(Shows showa : showsbd){
                    if(showa.dia == 9) {
                        show.add(showa);
                    }
                }
                break;
            case 10:
                for(Shows showa : showsbd){
                    if(showa.dia == 10) {
                        show.add(showa);
                    }
                }
                break;
            case 11:
                for(Shows showa : showsbd){
                    if(showa.dia == 11) {
                        show.add(showa);
                    }
                }
                break;
            case 12:
                for(Shows showa : showsbd){
                    if(showa.dia == 12) {
                        show.add(showa);
                    }
                }
                break;

            case 13:
                for(Shows showa : showsbd){
                    if(showa.dia == 13) {
                        show.add(showa);
                    }
                }
                break;
            case 14:
                for(Shows showa : showsbd){
                    if(showa.dia == 14) {
                        show.add(showa);
                    }
                }
                break;
            case 15:
                for(Shows showa : showsbd){
                    if(showa.dia == 15) {
                        show.add(showa);
                    }
                }

        }





        mAdapter_Shows = new RecyclerViewAdapterShow(TelaShowsPP.this, show);

        //recyclerView_Shows.setHasFixedSize(true);

        // use a linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView_Shows.setLayoutManager(layoutManager);


        recyclerView_Shows.setAdapter(mAdapter_Shows);


        scrollListener = new RecyclerViewScrollListener() {

            public void onEndOfScrollReached(RecyclerView rv) {

                // Toast.makeText(TelaShowsPP.this, "End of the RecyclerView reached. Do your pagination stuff here", Toast.LENGTH_SHORT).show();

                scrollListener.disableScrollListener();
            }
        };
        recyclerView_Shows.addOnScrollListener(scrollListener);
          /*
             Note: The below two methods should be used wisely to handle the pagination enable and disable states based on the use case.
                     1. scrollListener.disableScrollListener(); - Should be called to disable the scroll state.
                     2. scrollListener.enableScrollListener(); - Should be called to enable the scroll state.
          */


        recyclerView_Shows.addItemDecoration(new TelaAtracoesPP.GridSpacingItemDecoration(1, dpToPx(0), true));
        mAdapter_Shows.SetOnItemClickListener(new RecyclerViewAdapterShow.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Shows show) {

                //handle item click events here
                // Toast.makeText(TelaShowsPP.this, "Hey " + show.getTitle(), Toast.LENGTH_SHORT).show();
                //  Intent intent = new Intent(TelaShowsPP.this, TelaDetalhesPPNAOUSADO.class);
                //  intent.putExtra("show", show);
                //  startActivity(intent);

                //CardStackView a = findViewById(R.id.activity_main_card_stack_view);
                //a.setVisibility(View.VISIBLE);

                //spots.removeAll(spots);
//                spots.remove(0);
                adapter.clear();
                spots.removeAll(spots);

                spots.add(show);
                adapter.addAll(spots);
                adapter.notifyDataSetChanged();


                // reloadCardsDetalhes();


            }
        });



    }


//    private CardsDetalhes createTouristSpot() {
//        return new CardsDetalhes("Yasaka Shrine", "Kyoto", R.drawable.fabio);
//    }

//    private List<CardsDetalhes> createTouristSpots() {
//
//        //spots.add(new CardsDetalhes("Yasaka Shrine", "Kyoto", R.drawable.fabio));
//        return spots;
//    }

//    private CardsDetalhesAdapter createTouristSpotCardAdapter() {
//
//        adapter.addAll(spots);
//        return adapter;
//    }

    private void setupCardsDetalhes() {
        // progressBar = (ProgressBar) findViewById(R.id.activity_main_progress_bar);

        cardStackView = (CardStackView) findViewById(R.id.activity_main_card_stack_view);
        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                Log.d("CardStackView", "onCardDragging");
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                Log.d("CardStackView", "topIndex: " + cardStackView.getTopIndex());
                if (cardStackView.getTopIndex() == adapter.getCount() - 5) {
                    Log.d("CardStackView", "Paginate: " + cardStackView.getTopIndex());
                    //paginate();
                }
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });
    }

    private void reloadCardsDetalhes() {
        cardStackView.setVisibility(View.GONE);
        //progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // adapter = createTouristSpotCardAdapter();
                cardStackView.setAdapter(adapter);
                cardStackView.setVisibility(View.VISIBLE);
                //progressBar.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private LinkedList<Shows> extractRemainingTouristSpots() {
        LinkedList<Shows> spots = new LinkedList<>();
        for (int i = cardStackView.getTopIndex(); i < adapter.getCount(); i++) {
            spots.add(adapter.getItem(i));
        }
        return spots;
    }

//    private void addFirst() {
//        LinkedList<CardsDetalhes> spots = extractRemainingTouristSpots();
//       // spots.addFirst(shows);
//        adapter.clear();
//        adapter.addAll(spots);
//        adapter.notifyDataSetChanged();
//    }

//    private void addLast() {
//        LinkedList<CardsDetalhes> spots = extractRemainingTouristSpots();
//      //  spots.addLast(shows);
//        adapter.clear();
//        adapter.addAll(spots);
//        adapter.notifyDataSetChanged();
//    }

//    private void removeFirst() {
//        LinkedList<CardsDetalhes> spots = extractRemainingTouristSpots();
//        if (spots.isEmpty()) {
//            return;
//        }
//
//        spots.removeFirst();
//        adapter.clear();
//        adapter.addAll(spots);
//        adapter.notifyDataSetChanged();
//    }

//    private void removeLast() {
//        LinkedList<CardsDetalhes> spots = extractRemainingTouristSpots();
//        if (spots.isEmpty()) {
//            return;
//        }
//
//        spots.removeLast();
//        adapter.clear();
//        adapter.addAll(spots);
//        adapter.notifyDataSetChanged();
//    }

    private void paginate() {
        cardStackView.setPaginationReserved();
        adapter.addAll(spots);
        adapter.notifyDataSetChanged();
    }

    public void swipeLeft() {
        //List<CardsDetalhes> spots = extractRemainingTouristSpots();
        if (spots.isEmpty()) {
            return;
        }

        View target = cardStackView.getTopView();

        ValueAnimator rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", -10f));
        rotation.setDuration(200);
        ValueAnimator translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, -2000f));
        ValueAnimator translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f));
        translateX.setStartDelay(100);
        translateY.setStartDelay(100);
        translateX.setDuration(500);
        translateY.setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotation, translateX, translateY);

        cardStackView.swipe(SwipeDirection.Left, set);
    }

    public void swipeRight() {
        // List<CardsDetalhes> spots = extractRemainingTouristSpots();
        if (spots.isEmpty()) {
            return;
        }

        View target = cardStackView.getTopView();

        ValueAnimator rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", 10f));
        rotation.setDuration(200);
        ValueAnimator translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, 2000f));
        ValueAnimator translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f));
        translateX.setStartDelay(100);
        translateY.setStartDelay(100);
        translateX.setDuration(500);
        translateY.setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotation, translateX, translateY);

        cardStackView.swipe(SwipeDirection.Right, set);
    }

    private void reverse() {
        cardStackView.reverse();
    }




}

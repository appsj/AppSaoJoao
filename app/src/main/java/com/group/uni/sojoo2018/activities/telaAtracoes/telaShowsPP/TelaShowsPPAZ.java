package com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.support.v4.widget.SwipeRefreshLayout;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.detalhes.CardsDetalhes;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.detalhes.CardsDetalhesAdapter;
import com.group.uni.sojoo2018.utils.Tema;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.support.v4.view.MenuItemCompat;
import android.app.SearchManager;
import android.widget.EditText;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.Spanned;

import android.support.design.widget.FloatingActionButton;


public class TelaShowsPPAZ extends AppCompatActivity {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;
    private Toolbar toolbar;

    // @BindView(R.id.swipe_refresh_recycler_list)
    // SwipeRefreshLayout swipeRefreshRecyclerList;

    private SwipeRefreshLayout swipeRefreshRecyclerList;
    //@BindView(R.id.fab)
    //FloatingActionButton fab;
    private FloatingActionButton fab;
    private ShowsAdapterAZ mAdapter;

    private ArrayList<Shows> modelList = new ArrayList<>();

    private RecyclerViewAdapterShow mAdapter_Shows;
    private RecyclerView recyclerView_Shows;
    private CardsDetalhesAdapter adapter;
    List<Shows> spots = new ArrayList<>();
    CardsDetalhes shows;
    private ArrayList<Shows> show = new ArrayList<>();
    private CardStackView cardStackView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_shows_ppaz);

        // ButterKnife.bind(this);
        findViews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Tema.tema(toolbar, this);
        //setSupportActionBar(toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
        toolbarText.setText("Parque do Povo");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Ativa o botão voltar

        setAdapter();

        adapter = new CardsDetalhesAdapter(getApplicationContext());
        setupCardsDetalhes();
        reloadCardsDetalhes();
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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_search, menu);


        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search));

        SearchManager searchManager = (SearchManager) this.getSystemService(this.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

        //changing edittext color
        EditText searchEdit = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        searchEdit.setTextColor(Color.WHITE);
        searchEdit.setHintTextColor(Color.WHITE);
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchEdit.setHint("Search");

        InputFilter[] fArray = new InputFilter[2];
        fArray[0] = new InputFilter.LengthFilter(40);
        fArray[1] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {

                    if (!Character.isLetterOrDigit(source.charAt(i)))
                        return "";
                }


                return null;


            }
        };
        searchEdit.setFilters(fArray);
        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.TRANSPARENT);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Shows> filterList = new ArrayList<Shows>();
                if (s.length() > 0) {
                    for (int i = 0; i < modelList.size(); i++) {
                        if (modelList.get(i).getTitle().toLowerCase().contains(s.toString().toLowerCase())) {
                            filterList.add(modelList.get(i));
                            mAdapter.updateList(filterList);
                        }
                    }

                } else {
                    mAdapter.updateList(modelList);
                }
                return false;
            }
        });


        return true;
    }



    private void setAdapter() {


        ArrayList<Shows> singleItemList = new ArrayList<>();


        singleItemList.add(new Shows("Wesley Safadão", "Diferente não, estranho!", R.drawable.safadao));
        singleItemList.add(new Shows("Xand Avião", "Faz o x aí!", R.drawable.xand));

        //grupos

        modelList.add(new Shows("A", "", R.drawable.mastruz, singleItemList));

        singleItemList = new ArrayList<>();



        singleItemList.add(new Shows("A Loba", "Auuuuuuuu!", R.drawable.safadao));
        singleItemList.add(new Shows("Xand Avião", "Faz o x aí!", R.drawable.xand));

        modelList.add(new Shows("B", " ", R.drawable.mastruz, singleItemList));


        singleItemList = new ArrayList<>();



        singleItemList.add(new Shows("Magníficos", "Só paixão", R.drawable.safadao));
        singleItemList.add(new Shows("Forró das Antigas", "Faz o x aí!", R.drawable.xand));


        modelList.add(new Shows("C", "Hello " + " Marshmallow", R.drawable.mastruz, singleItemList));


        mAdapter = new ShowsAdapterAZ(TelaShowsPPAZ.this, modelList);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(TelaShowsPPAZ.this, R.drawable.divider_recyclerview));
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new ShowsAdapterAZ.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Shows model) {

                //handle item click events here
                  Toast.makeText(TelaShowsPPAZ.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();


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

                spots.add(model);
                adapter.addAll(spots);
                adapter.notifyDataSetChanged();


                // reloadCardsDetalhes();


            }
                });

            }



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
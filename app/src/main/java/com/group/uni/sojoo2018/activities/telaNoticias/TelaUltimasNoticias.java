package com.group.uni.sojoo2018.activities.telaNoticias;

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
import com.group.uni.sojoo2018.utils.Tema;

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


public class TelaUltimasNoticias extends AppCompatActivity {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;
    private Toolbar toolbar;
    TextView toolbarText;
    // @BindView(R.id.swipe_refresh_recycler_list)
    // SwipeRefreshLayout swipeRefreshRecyclerList;

    private SwipeRefreshLayout swipeRefreshRecyclerList;
    //@BindView(R.id.fab)
    //FloatingActionButton fab;
    private FloatingActionButton fab;
    private NoticiasAdapter mAdapter;

    private ArrayList<Noticias> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ultimasnoticias);

        // ButterKnife.bind(this);
        findViews();
        initToolbar("Notícias");
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarText = (TextView) findViewById(R.id.toolbar_text);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ;
    }

    public void initToolbar(String title) {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle(title);

        if (toolbarText != null && toolbar != null) {
            toolbarText.setText(title);
            toolbarText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            toolbarText.setPadding(0, 0, 100, 0);
        }
        Tema.tema(toolbar, this); //Ajustar o tamanho da Toolbat
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        searchEdit.setTextColor(getResources().getColor(R.color.titulo));
        searchEdit.setHintTextColor(getResources().getColor(R.color.titulo));
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchEdit.setHint("Procurar");

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
                ArrayList<Noticias> filterList = new ArrayList<Noticias>();
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


        modelList.add(new Noticias("Android", "Hello " + " Android", R.drawable.bgscrooll));
        modelList.add(new Noticias("Beta", "Hello " + " Beta", R.drawable.background));
        modelList.add(new Noticias("Cupcake", "Hello " + " Cupcake", R.drawable.safadao));
        modelList.add(new Noticias("Donut", "Hello " + " Donut", R.drawable.mastruz));
        modelList.add(new Noticias("Eclair", "Hello " + " Eclair", R.drawable.sj));
        modelList.add(new Noticias("Froyo", "Hello " + " Froyo", R.drawable.elba));
        modelList.add(new Noticias("Gingerbread", "Hello " + " Gingerbread", R.drawable.gleisson));
        modelList.add(new Noticias("Honeycomb", "Hello " + " Honeycomb", R.drawable.loba));
        modelList.add(new Noticias("Ice Cream Sandwich", "Hello " + " Ice Cream Sandwich", R.drawable.bgscrooll));
        modelList.add(new Noticias("Jelly Bean", "Hello " + " Jelly Bean", R.drawable.bgscrooll));
        modelList.add(new Noticias("KitKat", "Hello " + " KitKat", R.drawable.bgscrooll));
        modelList.add(new Noticias("Lollipop", "Hello " + " Lollipop", R.drawable.bgscrooll));
        modelList.add(new Noticias("Marshmallow", "Hello " + " Marshmallow", R.drawable.bgscrooll));
        modelList.add(new Noticias("Nougat", "Hello " + " Nougat", R.drawable.bgscrooll));
        modelList.add(new Noticias("Android O", "Hello " + " Android O", R.drawable.bgscrooll));


        mAdapter = new NoticiasAdapter(TelaUltimasNoticias.this, modelList);

        recyclerView.setHasFixedSize(true);


        final GridLayoutManager layoutManager = new GridLayoutManager(TelaUltimasNoticias.this, 2);
        recyclerView.addItemDecoration(new GridMarginDecoration(TelaUltimasNoticias.this, 2, 2, 2, 2));
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new NoticiasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Noticias model) {

                //handle item click events here
                Toast.makeText(TelaUltimasNoticias.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();


            }
        });


    }


}

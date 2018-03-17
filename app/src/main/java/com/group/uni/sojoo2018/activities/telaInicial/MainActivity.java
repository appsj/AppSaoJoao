package com.group.uni.sojoo2018.activities.telaInicial;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group.uni.sojoo2018.activities.telaAtracoes.TelaEventos;
import com.group.uni.sojoo2018.activities.telaAtracoes.telaShowsPP.TelaShowsPPAZ;
import com.group.uni.sojoo2018.activities.telaForrodometro.TelaForrodometro;
import com.group.uni.sojoo2018.activities.telaCultura.TelaCultura;
import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaNoticias.TelaUltimasNoticias;
import com.group.uni.sojoo2018.utils.Tema;
import com.group.uni.sojoo2018.activities.telaAtracoes.TelaAtracoes;
import com.group.uni.sojoo2018.activities.telaSobre.TelaSobre;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MenuInicialGrid adapter;
    Drawable overflow; //Icone botão da notificação
    private RecyclerView recyclerViewNotificacoes; //Lista de notificações
    private NotificacoesAdapter notificacoesAdapter;
    private List<Notificacao> notificacaoList;
    RelativeLayout layoutBGNotificacao;
    TextView notif;
    ImageView bandeiras;
    Toolbar toolbar;
    TextView toolbarText;
    NavigationView navigationView;
    DrawerLayout drawer;

    GridView grid;
    String[] web;
    int[] imageId = {
            //<editor-fold desc="Ícones">
            R.drawable.atracoes,
            R.drawable.como_chegar,
            R.drawable.cultura,
            R.drawable.news,
            R.drawable.melhores,
            R.drawable.sobre
            //</editor-fold>
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Bundle é o estado da tela (Inicia vazia)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Layout a ser inflado
        ButterKnife.bind(this);
        captarViews();



        criarNotificacao();
        //Settar o título da tela (Na Toolbar)

        if (toolbarText != null && toolbar != null) {
            toolbarText.setText("Bem-vindo!");
            toolbarText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            toolbarText.setPadding(0, 0, 100, 0);
        }
        Tema.tema(toolbar, this); //Ajustar o tamanho da Toolbat
        //Gerar background das notificações com cor aleatória
        layoutBGNotificacao = findViewById(R.id.relativeLayout);


        //Por fim, adiciona a toolbar na tela
        setSupportActionBar(toolbar);

        //<editor-fold desc="Drawer Aqui!">

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        //</editor-fold>


        criarMenus();

        ajustarLayoutNotificacoes();

        prepareNotificacoes();


        mudarCorBackground(); //Escolher cor aletória
        setUpItemTouchHelper();
        setUpAnimationDecoratorHelper();

    }

    private void captarViews() {

        web = new String[]{
                //<editor-fold desc="Titulos">
                this.getResources().getString(R.string.menu_atracoes),
                this.getResources().getString(R.string.menu_comoChegar),
                this.getResources().getString(R.string.menu_cultura),
                this.getResources().getString(R.string.menu_noticias),
                this.getResources().getString(R.string.menu_forrodometro),
                this.getResources().getString(R.string.menu_sobre)
                //</editor-fold>
        };
        toolbar = (Toolbar) findViewById(R.id.toolbar); //Capturando a (Toolbar) Barra de Ferramentas lá nos XML pelo o seu id, assim podemos controlá-lo por código
        notif = findViewById(R.id.nada);
        bandeiras = findViewById(R.id.bandeiras);
        layoutBGNotificacao = findViewById(R.id.relativeLayout);
        toolbarText = (TextView) findViewById(R.id.toolbar_text);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void criarMenus() {
        //<editor-fold desc="Menu">
        adapter = new MenuInicialGrid(MainActivity.this, web, imageId);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Toast.makeText(MainActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, TelaEventos.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, TelaShowsPPAZ.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, TelaCultura.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, TelaUltimasNoticias.class));
                        //startActivity(new Intent(MainActivity.this, com.group.uni.sojoo2018.activities.telaApresentacao.GradientBackgroundExampleActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, TelaForrodometro.class));
                        break;
                    case 5:

                        startActivity(new Intent(MainActivity.this, TelaSobre.class));
                        break;
                }

            }
        });
        //</editor-fold>
    }

    @Override
    public void onResume() { //Sempre que a tela for re-exibida (Ver ciclo de vida das Activities)
        super.onResume();
        mudarCorBackground(); //Muda de cor

    }

    public void ajustarLayoutNotificacoes() {

        //<editor-fold desc="Notificações">
        recyclerViewNotificacoes = (RecyclerView) findViewById(R.id.recycler_view);
        // overflow = getResources().getDrawable(R.drawable.icon_amei);
        //overflow.setColorFilter(getResources().getColor(R.color.hoje), null);
        //overflow.setColorFilter(ColorFilter);
        notificacaoList = new ArrayList<>();
        notificacoesAdapter = new NotificacoesAdapter(this, notificacaoList);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerViewNotificacoes.setLayoutManager(mLayoutManager);
        recyclerViewNotificacoes.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        recyclerViewNotificacoes.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotificacoes.setAdapter(notificacoesAdapter);
    }

    public void mudarCorBackground() {


        ImageView overflow = findViewById(R.id.action);


        Random gerador = new Random(); //Gerador de caracteres aleatórios

        int cor = gerador.nextInt(4); //Gera um inteiro entre 0 e 4
        Window window = this.getWindow();

        switch (cor) {
            case 0:
                layoutBGNotificacao.setBackground(getResources().getDrawable(R.drawable.shape_azul));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

                    //overflow.setColorFilter(getResources().getColor(R.color.hoje));
                    window.setNavigationBarColor(getResources().getColor(R.color.hoje));
                }
                break;
            case 1:
                layoutBGNotificacao.setBackground(getResources().getDrawable(R.drawable.shape_azul));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                    //    overflow.setColorFilter(getResources().getColor(R.color.hoje));
                    window.setNavigationBarColor(getResources().getColor(R.color.hoje));
                }
                break;
            case 2:

                layoutBGNotificacao.setBackground(getResources().getDrawable(R.drawable.shape_laranja));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                    //  overflow.setColorFilter(Color.BLUE);
                    window.setNavigationBarColor(getResources().getColor(R.color.laranja));
                }
                break;
            case 3:
                layoutBGNotificacao.setBackground(getResources().getDrawable(R.drawable.shape_preto));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                    //overflow.setColorFilter(getResources().getColor(R.color.preto));
                    window.setNavigationBarColor(getResources().getColor(R.color.preto));
                }
                break;
            case 4:
                layoutBGNotificacao.setBackground(getResources().getDrawable(R.drawable.shape_rosa));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                    //overflow.setColorFilter(getResources().getColor(R.color.rosa));
                    window.setNavigationBarColor(getResources().getColor(R.color.rosa));
                }
                break;
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Notificacao a = new Notificacao("Teste", "Teste", R.drawable.crazy, 2);
            notificacaoList.add(a);
            notificacoesAdapter.notifyDataSetChanged();


            notif.setVisibility(View.INVISIBLE);
            bandeiras.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds notificacaoList to the action bar if it is present.


        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Exibe a notificacao
    protected void criarNotificacao() {
        NotificationManager manager;
        Notification myNotication;

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        //API level 11
        Intent intent = new Intent("com.rj.notitfications.SECACTIVITY");

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1, intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.icon512a)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.xand))
                .setContentTitle("Ei rapaz!")
                .setContentText("Tais de bobeira aí? Vem curtir Xand Avião hoje")
                .setAutoCancel(true).setColor(getResources().getColor(R.color.transparente))
                // .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        android.app.NotificationManager notificationManager =
                (android.app.NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    /**
//     * Initializing collapsing toolbar
//     * Will show and hide the toolbar title on scroll
//     */
//    private void initCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(" ");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.setExpanded(true);
//
//        // hiding & showing the title when toolbar expanded & collapsed
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    collapsingToolbar.setTitle(getString(R.string.app_name));
//                    isShow = true;
//                } else if (isShow) {
//                    collapsingToolbar.setTitle(" ");
//                    isShow = false;
//                }
//            }
//        });
//    }

    /**
     * Adicionar notificações
     */
    private void prepareNotificacoes() {
        int[] covers = new int[]{
                R.drawable.xand,
                R.drawable.fabio,
                R.drawable.larissa,
                R.drawable.bgscrooll
        };

        Notificacao a = new Notificacao("Hoje tem Xandy Avião!!", "Riquelme na batera bota pra descer!", covers[0], 1);
        notificacaoList.add(a);

        a = new Notificacao("Vai ficar em casa?", " A grande festa começa hoje!", covers[1], 2);
        notificacaoList.add(a);

        a = new Notificacao("Compartilhe com os seus amigos", "A festa fica melhor com quem você gosta!", covers[2], 2);
        notificacaoList.add(a);

        a = new Notificacao("Você sabe a origem do São João?", "Confira essa matéria!", covers[3], 3);
        notificacaoList.add(a);


        notificacoesAdapter.notifyDataSetChanged();
    }

    /**
     * Ajustar o layout das notificações
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

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


    private void setUpItemTouchHelper() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // we want to cache these and not allocate anything repeatedly in the onChildDraw method
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(getResources().getColor(R.color.hoje));
                xMark = ContextCompat.getDrawable(MainActivity.this, R.drawable.frame);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                xMarkMargin = (int) MainActivity.this.getResources().getDimension(R.dimen.card_margin);
                initiated = true;
            }

            // not important, we don't want drag & drop
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                NotificacoesAdapter testAdapter = (NotificacoesAdapter) recyclerView.getAdapter();
                if (testAdapter.isUndoOn() && testAdapter.isPendingRemoval(position)) {
                    return 0;
                }
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                NotificacoesAdapter adapter = (NotificacoesAdapter) recyclerViewNotificacoes.getAdapter();
                boolean undoOn = adapter.isUndoOn();
                if (undoOn) {
                    adapter.pendingRemoval(swipedPosition);
                } else {
                    adapter.remove(swipedPosition);
                    if (notificacoesAdapter.getItemCount() == 0) {

                        notif.setVisibility(View.VISIBLE);
                        bandeiras.setVisibility(View.VISIBLE);
                        //  notif.setText("vazioooooo");
                    }
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.getAdapterPosition() == -1) {
                    // not interested in those
                    return;
                }

                if (!initiated) {
                    init();
                }

                // draw red background
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = xMark.getIntrinsicWidth();
                int intrinsicHeight = xMark.getIntrinsicWidth();

                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                int xMarkRight = itemView.getRight() - xMarkMargin;
                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerViewNotificacoes);
    }

    /**
     * We're gonna setup another ItemDecorator that will draw the red background in the empty space while the notificacaoList are animating to thier new positions
     * after an item is removed.
     */
    private void setUpAnimationDecoratorHelper() {
        recyclerViewNotificacoes.addItemDecoration(new RecyclerView.ItemDecoration() {

            // we want to cache this and not allocate anything repeatedly in the onDraw method
            Drawable background;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(getResources().getColor(R.color.hojedark));
                initiated = true;
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

                if (!initiated) {
                    init();
                }

                // only if animation is in progress
                if (parent.getItemAnimator().isRunning()) {

                    // some notificacaoList might be animating down and some notificacaoList might be animating up to close the gap left by the removed item
                    // this is not exclusive, both movement can be happening at the same time
                    // to reproduce this leave just enough notificacaoList so the first one and the last one would be just a little off screen
                    // then remove one from the middle

                    // find first child with translationY > 0
                    // and last one with translationY < 0
                    // we're after a rect that is not covered in recycler-view views at this point in time
                    View lastViewComingDown = null;
                    View firstViewComingUp = null;

                    // this is fixed
                    int left = 0;
                    int right = parent.getWidth();

                    // this we need to find out
                    int top = 0;
                    int bottom = 0;

                    // find relevant translating views
                    int childCount = parent.getLayoutManager().getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View child = parent.getLayoutManager().getChildAt(i);
                        if (child.getTranslationY() < 0) {
                            // view is coming down
                            lastViewComingDown = child;
                        } else if (child.getTranslationY() > 0) {
                            // view is coming up
                            if (firstViewComingUp == null) {
                                firstViewComingUp = child;
                            }
                        }
                    }

                    if (lastViewComingDown != null && firstViewComingUp != null) {
                        // views are coming down AND going up to fill the void
                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
                    } else if (lastViewComingDown != null) {
                        // views are going down to fill the void
                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
                        bottom = lastViewComingDown.getBottom();
                    } else if (firstViewComingUp != null) {
                        // views are coming up to fill the void
                        top = firstViewComingUp.getTop();
                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
                    }

                    background.setBounds(left, top, right, bottom);
                    background.draw(c);

                }
                super.onDraw(c, parent, state);
            }

        });
    }

}



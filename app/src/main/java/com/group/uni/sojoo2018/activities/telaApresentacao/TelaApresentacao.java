package com.group.uni.sojoo2018.activities.telaApresentacao;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaLogin.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class TelaApresentacao extends ApresentacaoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApresentacaoCard apresentacaoCard1 = new ApresentacaoCard("App São João 20018", "Bem-vindo ao Maior São João do Mundo! Deslize para conferir o que temos para você...", R.mipmap.icon512a);
        ApresentacaoCard apresentacaoCard2 = new ApresentacaoCard("Como Chegar?", "Não fique perdido, encontre os melhores locais para aumentar sua diversão!", R.drawable.como_chegar);
        ApresentacaoCard apresentacaoCard3 = new ApresentacaoCard("Quem Canta Hoje?", "Confira quem vai animar a sua noite!", R.drawable.atracoes);
        ApresentacaoCard apresentacaoCard4 = new ApresentacaoCard("Fique por Dentro!", "Confira as principais notícias dessa grande festa!", R.drawable.news);

        apresentacaoCard1.setBackgroundColor(R.color.black_transparent);
        apresentacaoCard2.setBackgroundColor(R.color.black_transparent);
        apresentacaoCard3.setBackgroundColor(R.color.black_transparent);
        apresentacaoCard4.setBackgroundColor(R.color.black_transparent);
        setGradientBackground();
        List<ApresentacaoCard> pages = new ArrayList<>();

        pages.add(apresentacaoCard1);
        pages.add(apresentacaoCard2);
        pages.add(apresentacaoCard3);
        pages.add(apresentacaoCard4);

        for (ApresentacaoCard page : pages) {
            page.setTitleColor(R.color.cardview_light_background);
            page.setDescriptionColor(R.color.grey_200);
        }
        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        setFinishButtonTitle("Começar");
        showNavigationControls(true);
        //setGradientBackground();
        //setImageBackground(R.mipmap.icon512a);

//        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        //setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.cardview_light_background);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(TelaApresentacao.this, LoginActivity.class));
        //Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
    }
}

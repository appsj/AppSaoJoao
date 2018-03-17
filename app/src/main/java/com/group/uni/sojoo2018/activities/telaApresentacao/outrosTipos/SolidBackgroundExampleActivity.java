package com.group.uni.sojoo2018.activities.telaApresentacao.outrosTipos;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaApresentacao.ApresentacaoActivity;
import com.group.uni.sojoo2018.activities.telaApresentacao.ApresentacaoCard;

import java.util.ArrayList;
import java.util.List;

public class SolidBackgroundExampleActivity extends ApresentacaoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApresentacaoCard apresentacaoCard1 = new ApresentacaoCard("Scan Barcode", "Label your packages with a barcode before we collect it from you.", R.mipmap.icon512a);
        ApresentacaoCard apresentacaoCard2 = new ApresentacaoCard("Shipping", "Our huge network of shipping partners ensures that your packages are always on schedule.", R.drawable.atracoes);
        ApresentacaoCard apresentacaoCard3 = new ApresentacaoCard("Payment", "Receive payments immediately after the package is delivered.", R.drawable.como_chegar);

        apresentacaoCard1.setBackgroundColor(R.color.cardview_light_background);
        apresentacaoCard2.setBackgroundColor(R.color.cardview_light_background);
        apresentacaoCard3.setBackgroundColor(R.color.cardview_light_background);

        List<ApresentacaoCard> pages = new ArrayList<>();

        pages.add(apresentacaoCard1);
        pages.add(apresentacaoCard2);
        pages.add(apresentacaoCard3);

        for (ApresentacaoCard page : pages) {
            page.setTitleColor(R.color.preto);
            page.setDescriptionColor(R.color.grey_600);
        }

        setFinishButtonTitle("Finish");
        showNavigationControls(false);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.azul);
        colorList.add(R.color.laranja);
        colorList.add(R.color.laranja);

        setColorBackground(colorList);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        setFont(face);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
    }
}

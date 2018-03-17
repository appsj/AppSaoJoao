package com.group.uni.sojoo2018.activities.telaApresentacao.outrosTipos;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaApresentacao.ApresentacaoActivity;
import com.group.uni.sojoo2018.activities.telaApresentacao.ApresentacaoCard;

import java.util.ArrayList;
import java.util.List;

public class GradientBackgroundExampleActivity extends ApresentacaoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApresentacaoCard apresentacaoCard1 = new ApresentacaoCard("City Guide", "Detailed guides to help you plan your trip.", R.mipmap.icon512a);
        ApresentacaoCard apresentacaoCard2 = new ApresentacaoCard("Travel Blog", "Share your travel experiences with a vast network of fellow travellers.", R.drawable.atracoes);
        ApresentacaoCard apresentacaoCard3 = new ApresentacaoCard("Chat", "Connect with like minded people and exchange your travel stories.", R.drawable.como_chegar);

        apresentacaoCard1.setBackgroundColor(R.color.black_transparent);
        apresentacaoCard2.setBackgroundColor(R.color.black_transparent);
        apresentacaoCard3.setBackgroundColor(R.color.black_transparent);

        List<ApresentacaoCard> pages = new ArrayList<>();

        pages.add(apresentacaoCard1);
        pages.add(apresentacaoCard2);
        pages.add(apresentacaoCard3);

        for (ApresentacaoCard page : pages) {
            page.setTitleColor(R.color.cardview_light_background);
            page.setDescriptionColor(R.color.grey_200);
            //page.setTitleTextSize(dpToPixels(12, this));
            //page.setDescriptionTextSize(dpToPixels(8, this));
            //page.setIconLayoutParams(width, height, marginTop, marginLeft, marginRight, marginBottom);
        }

        setFinishButtonTitle("Finish");
        showNavigationControls(true);
        setGradientBackground();

        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }

//        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
//        setFont(face);

        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
    }
}

package com.group.uni.sojoo2018.activities.telaSplash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;


import com.group.uni.sojoo2018.R;
import com.group.uni.sojoo2018.activities.telaApresentacao.TelaApresentacao;
import com.group.uni.sojoo2018.activities.telaInicial.MainActivity;

import studios.codelight.smartloginlibrary.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartUser;

public class SplashScreen extends AppCompatActivity {
    private ExplosionField mExplosionField;
    SmartUser currentUser;
    // Timer da splash screen
    private static int SPLASH_TIME_OUT = 0500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Splash.Builder splash = new Splash.Builder(this, getSupportActionBar());
//        splash.setSplashImage(getResources().getDrawable(R.mipmap.icon512a));
//        splash.setSplashImageColor(getResources().getColor(R.color.transparente));
//        splash.perform();
        mExplosionField = ExplosionField.attach2Window(this);
        addListener(findViewById(R.id.splash));
        View view = findViewById(R.id.splash);

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */

            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                currentUser = UserSessionManager.getCurrentUser(SplashScreen.this);
                if (currentUser != null) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashScreen.this, TelaApresentacao.class));


                }

                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    private void addListener(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                addListener(parent.getChildAt(i));
            }
        } else {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExplosionField.explode(v);
                    v.setOnClickListener(null);
                }
            });
        }
    }


    private void reset(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                reset(parent.getChildAt(i));
            }
        } else {
            root.setScaleX(1);
            root.setScaleY(1);
            root.setAlpha(1);
        }
    }
    //Aqui será pra fazer sincronização com servidor antes de iniciar o app
}

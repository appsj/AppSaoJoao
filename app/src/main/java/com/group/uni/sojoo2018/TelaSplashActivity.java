package com.group.uni.sojoo2018;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.group.uni.sojoo2018.activities.telaApresentacao.TelaApresentacao;
import com.group.uni.sojoo2018.activities.telaInicial.MainActivity;
import com.group.uni.sojoo2018.activities.telaSplash.SplashScreen;
import com.plattysoft.leonids.ParticleSystem;

import studios.codelight.smartloginlibrary.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartUser;

public class TelaSplashActivity extends AppCompatActivity {
    RelativeLayout l1,l2;
    Button btnsub;
    Animation uptodown,downtoup;
    SmartUser currentUser;
    private static int SPLASH_TIME_OUT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);
        fogos();
        l2 = (RelativeLayout) findViewById(R.id.l2);
        //   uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        //  l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */

            @Override
            public void run() {

                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                currentUser = UserSessionManager.getCurrentUser(TelaSplashActivity.this);
                if (currentUser != null) {
                    startActivity(new Intent(TelaSplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(TelaSplashActivity.this, TelaApresentacao.class));


                }

                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
    public void fogos() {
        new ParticleSystem(this, 40, R.drawable.confeti2, 5000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
                .setRotationSpeed(144)
                .setAcceleration(0.001197f, 60)
                .emit(findViewById(R.id.emiter_top_right), 10);

        new ParticleSystem(this, 40, R.drawable.confeti3, 5000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.001197f, 60)
                .emit(findViewById(R.id.emiter_top_left), 10);
    }

}

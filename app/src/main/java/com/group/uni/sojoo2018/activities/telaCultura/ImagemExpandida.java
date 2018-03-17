package com.group.uni.sojoo2018.activities.telaCultura;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.group.uni.sojoo2018.R;

public class ImagemExpandida extends AppCompatActivity {


    ImageView imageview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Mudar pra pegar a imagem baixada
        setContentView(R.layout.view_image);

        // Intent i = getIntent();

        // String[] filepath = i.getStringArrayExtra("filepath");

        //  int a = i.getIntExtra("filepath");


        imageview = (ImageView) findViewById(R.id.full_image_view);

        //  Bitmap bmp = BitmapFactory.decodeFile(i.getBundleExtra("filepath"));
        imageview.setImageResource(R.drawable.sj);
        //imageview.setImageBitmap(R.drawable.sj);

    }
}

package com.group.uni.sojoo2018.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.group.uni.sojoo2018.R;

/**
 * Created by fabii on 10/02/2018.
 */

public class Tema {


    public static void tema(Toolbar toolbar, Activity context) {


        toolbar.setPadding(0, getStatusBarHeight(context), 0, 0);
        toolbar.getBackground().setAlpha(0);
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.titulo));
        toolbar.setOverflowIcon(context.getResources().getDrawable(R.drawable.perfil));

        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

//        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.titulo), PorterDuff.Mode.MULTIPLY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = context.getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
    }

    //utils
    public static int getStatusBarHeight( Activity context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

package com.travelfoots.ntitreetravelfoots;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_TIME = 2900;
    Typeface fontNanum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initFont();
        LottieAnimationView lottie = findViewById(R.id.Splash);
        TextView textView = findViewById(R.id.textView2);
        setFont(textView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(0, android.R.anim.fade_in);

                lottie.setRepeatMode(0);
                lottie.playAnimation();
                lottie.cancelAnimation();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_TIME);


    }

    void initFont() {
        fontNanum = Typeface.createFromAsset(this.getAssets(), "Modeka.otf");
        fontNanum = Typeface.createFromAsset(this.getAssets(), "Modeka.otf");
    }

    void setFont(TextView textView) {
        textView.setTypeface(fontNanum,Typeface.BOLD);
    }


}


package com.roushan.verboseassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView icon, text;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        icon = (ImageView) findViewById(R.id.qd_icon);
        text = (ImageView) findViewById(R.id.qd_text);

        Animation img_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_down);
        Animation txt_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_up);
        icon.setAnimation(img_animation);
        text.setAnimation(txt_animation);

        img_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                session = new Session(SplashActivity.this);
                if(session.checkLogin()) {     // session.checkLogin() == true
                    startActivity(new Intent(SplashActivity.this, BuyerSellerNavActivity.class));
                    finish();
                } else {
                    finish();
                    startActivity(new Intent(SplashActivity.this, SelectBuyerSeller.class));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

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

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        icon.setAnimation(animation);
        text.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                session = new Session(SplashActivity.this);
                if(session.checkLogin()) {     // session.checkLogin() == true
                    startActivity(new Intent(getApplicationContext(), BuyerSellerNavActivity.class));
                    finish();
                } else {
                    finish();
                    startActivity(new Intent(getApplicationContext(), SelectBuyerSeller.class));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

package com.hamza.counter;


import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;


public class SplashScreen extends AppCompatActivity {
TextView welcomeText;
int timeSleep = 5000;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        welcomeText=findViewById(R.id.welcomView);
        setAnimationToText();
         sleepActivity();

    }

    public void setAnimationToText(){
        animation =  AnimationUtils.loadAnimation(getBaseContext(),R.anim.text_anim);
        welcomeText.setAnimation(animation);
    }
public void sleepActivity(){
 new Handler().postDelayed(new Runnable() {@Override public void run() { goToMainActivite(); }},timeSleep);
}
public void goToMainActivite(){
    Intent intent = new Intent(getBaseContext(),MainActivity.class);
    startActivity(intent);
    finish();
}

}



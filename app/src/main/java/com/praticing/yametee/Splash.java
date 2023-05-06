package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // this is a method in a splash screen, you can see the splash screen
        // at the beginning of the application
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //SplashScreen to LoginSystem
                Intent intent = new Intent(Splash.this,LoginSystem.class);
                startActivity(intent);
            }
            //Delaying in 2500 which means in sec is 2.5sec
        }, 2500);
    }
}
package com.nschirmer.marvelheroes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(300);

        }catch (InterruptedException e) {}

        startActivity(new Intent(this, HeroesListActivity.class));
        finish();
    }

}

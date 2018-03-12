package com.nschirmer.marvelheroes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nschirmer.marvelheroes.R;

public class HeroesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);
    }
}

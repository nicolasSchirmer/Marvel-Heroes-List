package com.nschirmer.marvelheroes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nschirmer.marvelheroes.rest.models.CharactersResult;
import com.nschirmer.marvelheroes.R;
import com.nschirmer.marvelheroes.rest.MarvelApiService;
import com.nschirmer.marvelheroes.utils.ActivityUtils;

public class HeroesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);
        ActivityUtils.setStrechActivity(this);

        MarvelApiService marvelApiService = new MarvelApiService();

        marvelApiService.getAllCharacters(new MarvelApiService.CallListener() {
            @Override
            public void sucess(CharactersResult charactersResult) {
                Toast.makeText(HeroesListActivity.this, charactersResult.getCopyright(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failed(String errorMessage) {
                Toast.makeText(HeroesListActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}

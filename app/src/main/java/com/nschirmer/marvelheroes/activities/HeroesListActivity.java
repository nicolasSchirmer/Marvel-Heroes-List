package com.nschirmer.marvelheroes.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nschirmer.marvelheroes.adapters.HeroesListAdapter;
import com.nschirmer.marvelheroes.rest.models.Result;
import com.nschirmer.marvelheroes.R;
import com.nschirmer.marvelheroes.rest.MarvelApiService;
import com.nschirmer.marvelheroes.utils.ActivityUtils;

import java.util.List;

public class HeroesListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);
        ActivityUtils.setStrechActivity(this);

        MarvelApiService marvelApiService = new MarvelApiService();

        marvelApiService.getCharacters( 0, new MarvelApiService.CharactersCallListener() {
            @Override
            public void sucess(List<Result> results) {
                populateList(results);
            }

            @Override
            public void failed(String errorMessage) {
                Toast.makeText(HeroesListActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void populateList(List<Result> results){
        ViewPager viewPager = findViewById(R.id.herores_list_view_pager);

        float density = getResources().getDisplayMetrics().density;
        int partialWidth = (int) (16 * density); // 16dp
        int pageMargin = (int) (8 * density); // 8dp

        int viewPagerPadding = partialWidth + pageMargin;

        viewPager.setPageMargin(pageMargin);
        viewPager.setPadding(viewPagerPadding, 0, viewPagerPadding, 0);

        viewPager.setClipToPadding(false);

        HeroesListAdapter heroesListAdapter = new HeroesListAdapter(getSupportFragmentManager(), results);
        viewPager.setAdapter(heroesListAdapter);
    }
}

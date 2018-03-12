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

import java.util.ArrayList;
import java.util.List;

public class HeroesListActivity extends AppCompatActivity {

    private List<Result> results = new ArrayList<>();
    private MarvelApiService marvelApiService;
    private HeroesListAdapter heroesListAdapter;
    private int offset = 0;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);
        ActivityUtils.setStrechActivity(this);

        marvelApiService = new MarvelApiService();

        viewPager = findViewById(R.id.herores_list_view_pager);
        setUpViewPager();

        getCharactersFromOfsset(offset);
    }


    private void getCharactersFromOfsset(int offset){
        marvelApiService.getCharacters( offset, new MarvelApiService.CharactersCallListener() {
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
        this.results.addAll(results);
        heroesListAdapter.notifyDataSetChanged();
    }


    private void setUpViewPager(){
        heroesListAdapter = new HeroesListAdapter(getSupportFragmentManager(), results);
        viewPager.setAdapter(heroesListAdapter);

        float density = getResources().getDisplayMetrics().density;
        int partialWidth = (int) (16 * density); // 16dp
        int pageMargin = (int) (8 * density); // 8dp

        int viewPagerPadding = partialWidth + pageMargin;

        viewPager.setPageMargin(pageMargin);
        viewPager.setPadding(viewPagerPadding, 0, viewPagerPadding, 0);

        viewPager.setClipToPadding(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == results.size() -3){
                    offset += 10;
                    getCharactersFromOfsset(offset);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

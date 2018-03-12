package com.nschirmer.marvelheroes.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.nschirmer.marvelheroes.rest.models.Result;

import com.nschirmer.marvelheroes.fragments.HeroListItemFragment;

import java.util.ArrayList;
import java.util.List;

public class HeroesListAdapter extends FragmentPagerAdapter {

    private List<Result> results = new ArrayList<>();

    public HeroesListAdapter(FragmentManager fragmentManager, List<Result> results) {
        super(fragmentManager);
        this.results = results;
    }

    @Override
    public Fragment getItem(int position) {
        return HeroListItemFragment.newInstance(results.get(position));
    }

    @Override
    public int getCount() {
        return results.size();
    }
}

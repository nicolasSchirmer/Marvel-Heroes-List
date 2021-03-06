package com.nschirmer.marvelheroes.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.nschirmer.marvelheroes.R;
import com.nschirmer.marvelheroes.activities.CharacterActivity;
import com.nschirmer.marvelheroes.rest.models.Result;
import com.nschirmer.marvelheroes.utils.Dictionary;

public class HeroListItemFragment extends Fragment {

    private String characterJSON;

    public static HeroListItemFragment newInstance(Result result){
        HeroListItemFragment heroListItemFragment = new HeroListItemFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Dictionary.CHATACYER_GSON, new Gson().toJson(result));

        heroListItemFragment.setArguments(bundle);

        return heroListItemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.heroe_list_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            SimpleDraweeView photo = view.findViewById(R.id.hero_list_item_photo);
            photo.setOnClickListener(onClickListener);
            TextView name = view.findViewById(R.id.hero_list_item_name);
            name.setOnClickListener(onClickListener);
            TextView description = view.findViewById(R.id.hero_list_item_description);
            description.setOnClickListener(onClickListener);

            characterJSON = getArguments().getString(Dictionary.CHATACYER_GSON);
            Result result = new Gson().fromJson(characterJSON, Result.class);

            photo.setImageURI(Uri.parse(result.getThumbnail().getPath()) + "." + result.getThumbnail().getExtension());
            name.setText(result.getName());
            description.setText(result.getDescription());
        }
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().startActivity(new Intent(getContext(), CharacterActivity.class).putExtra(Dictionary.CHATACYER_GSON, characterJSON));
        }
    };
}

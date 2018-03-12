package com.nschirmer.marvelheroes.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.nschirmer.marvelheroes.R;
import com.nschirmer.marvelheroes.rest.models.Result;
import com.nschirmer.marvelheroes.utils.Dictionary;

public class CharacterActivity extends AppCompatActivity {

    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        String characterJson = getIntent().getStringExtra(Dictionary.CHATACYER_GSON);
        result = new Gson().fromJson(characterJson, Result.class);

        SimpleDraweeView photo = findViewById(R.id.character_photo);
        TextView name = findViewById(R.id.character_name);
        TextView description = findViewById(R.id.character_description);
        TextView comics = findViewById(R.id.character_comics);
        TextView events = findViewById(R.id.character_events);
        TextView series = findViewById(R.id.character_series);
        TextView stories = findViewById(R.id.character_stories);

        photo.setImageURI(Uri.parse(result.getThumbnail().getPath()) + "." + result.getThumbnail().getExtension());
        name.setText(result.getName());
        description.setText(result.getDescription());
        comics.setText(getString(R.string.comics, result.getComics().getAvailable()));
        events.setText(getString(R.string.events, result.getEvents().getAvailable()));
        series.setText(getString(R.string.series, result.getSeries().getAvailable()));
        stories.setText(getString(R.string.stories, result.getStories().getAvailable()));
    }

    public void backButtonClick(View view){
        onBackPressed();
    }
}

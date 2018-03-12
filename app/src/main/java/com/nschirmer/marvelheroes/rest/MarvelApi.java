package com.nschirmer.marvelheroes.rest;

import com.nschirmer.marvelheroes.rest.models.CharactersResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApi {

    String NAME = "name";
    String API_KEY = "apikey";
    String HASH = "hash";
    String TIMESTAMP = "ts";

    String BASE_URL = "http://gateway.marvel.com/v1/public/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("characters")
    Call<CharactersResult> getAllCharacters(@Query(TIMESTAMP) String timestamp, @Query(API_KEY) String apiKey, @Query(HASH) String hash);


}

package com.nschirmer.marvelheroes.rest;

import android.support.annotation.NonNull;

import com.nschirmer.marvelheroes.rest.models.Result;
import com.nschirmer.marvelheroes.rest.models.CharactersResult;
import com.nschirmer.marvelheroes.utils.HashAuthGenerator;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarvelApiService {

    private MarvelApi marvelApi = MarvelApi.retrofit.create(MarvelApi.class);

    private final int limit = 10;

    public interface CharactersCallListener {
        void sucess(List<Result> results);
        void failed(String errorMessage);
    }


    public void getCharacters(int offset, final CharactersCallListener charactersCallListener){
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = HashAuthGenerator.generateHash(timestamp, KeysUtil.publicKey, KeysUtil.privateKey);
            Call<CharactersResult> charactersResultCall = marvelApi.getAllCharacters(timestamp, KeysUtil.publicKey, hash, limit, offset);
            charactersResultCall.enqueue(new Callback<CharactersResult>() {
                @Override
                public void onResponse(@NonNull Call<CharactersResult> call, @NonNull Response<CharactersResult> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        CharactersResult charactersResult = response.body();
                        assert charactersResult != null;
                        charactersCallListener.sucess(charactersResult.getData().getResults());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<CharactersResult> call, @NonNull Throwable t) {
                    charactersCallListener.failed(call.toString());
                }
            });


        } catch (NoSuchAlgorithmException e) {
            charactersCallListener.failed(e.getMessage());
        }
    }
}

package com.nschirmer.marvelheroes.rest;

import android.support.annotation.NonNull;

import com.nschirmer.marvelheroes.rest.models.CharactersResult;
import com.nschirmer.marvelheroes.utils.HashAuthGenerator;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarvelApiService {

    private MarvelApi marvelApi = MarvelApi.retrofit.create(MarvelApi.class);

    public interface CallListener {
        void sucess(CharactersResult charactersResult);
        void failed(String errorMessage);
    }

    public void getAllCharacters(final CallListener callListener) {
        String timestamp = String.valueOf(System.currentTimeMillis());

        try {
            String hash = HashAuthGenerator.generateHash(timestamp, KeysUtil.publicKey, KeysUtil.privateKey);

            Call<CharactersResult> charactersResultCall = marvelApi.getAllCharacters(timestamp, KeysUtil.publicKey, hash);

            charactersResultCall.enqueue(new Callback<CharactersResult>() {
                @Override
                public void onResponse(@NonNull Call<CharactersResult> call, @NonNull Response<CharactersResult> response) {
                    if(response.body() != null){
                        callListener.sucess(response.body());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<CharactersResult> call, @NonNull Throwable t) {
                    callListener.failed(call.toString());
                }
            });


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}

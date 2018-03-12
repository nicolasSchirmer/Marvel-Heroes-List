package com.nschirmer.marvelheroes;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class ApplicationInit extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}

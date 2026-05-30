package com.mirea.Samsonova.yandexdriver;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {

    private static final String MAPKIT_API_KEY = "a640a3a9-fbdb-4336-8ed1-7f79fc2c1801";

    @Override
    public void onCreate() {
        super.onCreate();

        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}
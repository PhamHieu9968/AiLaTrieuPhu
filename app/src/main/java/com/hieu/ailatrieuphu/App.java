package com.hieu.ailatrieuphu;

import android.app.Application;

import androidx.room.Room;

import com.hieu.ailatrieuphu.db.AppDB;

public class App extends Application {
    private static App instance;
    private Storage storage;
    private AppDB db;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new Storage();
        db = Room.databaseBuilder(this,AppDB.class,"Question")
                .createFromAsset("db/Question")
                .build();
    }

    public AppDB getDb() {
        return db;
    }

    public Storage getStorage() {
        return storage;
    }
    public static App getInstance() {
        return instance;
    }
}

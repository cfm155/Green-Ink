package com.example.cartermccall.greenink;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("newrealm.realm").schemaVersion(1).build();
        Realm.setDefaultConfiguration(config);
    }
}

package com.saifeeeformula.saif_win10.saifdrawer;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class EEEformula extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}

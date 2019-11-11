package com.saifeeeformula.saif_win10.saifdrawer;

import android.app.Application;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class GetLinkFromFirebase extends Application {
    String firebaseLink = "https://eee-formula.firebaseio.com/";
    String[] setLink;
    String j,k,l,m,n,child_name, key_name;

    public GetLinkFromFirebase(String child_name, String key_name)
    {
        this.child_name = child_name;
        this.key_name = key_name;
    }

    Firebase getJ = new Firebase(firebaseLink+child_name+key_name+"j");



}

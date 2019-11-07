package com.saifeeeformula.saif_win10.saifdrawer;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import static android.content.Context.CONNECTIVITY_SERVICE;

public class FragmentImgView extends Fragment {

    private FragmentImgViewViewModel mViewModel;
    private String firebaseLink = "https://eee-formula.firebaseio.com/";
    private String j,k,l,m,n,child_name, key_name;
    private ImageView mImg_1, mImg_2, mImg_3, mImg_4, mImg_5;
    private PhotoViewAttacher mAttacher;

    private ConnectivityManager connectivityManager;

    private static final String TAG = "FragmentImgView";

    // step 1: add some instance //For Scroll Zoom effect
    private float mScale = 1f;
    private ScaleGestureDetector mScaleDetector;
    GestureDetector gestureDetector;
    ScrollView layout;

    public static FragmentImgView newInstance() {
        return new FragmentImgView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_img_view_fragment, container, false);
        Firebase.setAndroidContext(Objects.requireNonNull(getContext()));

        assert getArguments() != null;
        child_name = getArguments().getString("child_name");
        key_name = getArguments().getString("key_name");

        mImg_1 = rootView.findViewById(R.id.mImg_1);
        // Set the Drawable displayed
        Drawable bitmap = getResources().getDrawable(R.drawable.ic_menu_camera);
        mImg_1.setImageDrawable(bitmap);
        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        mAttacher = new PhotoViewAttacher(mImg_1);

        mImg_2 = rootView.findViewById(R.id.mImg_2);
        mImg_2.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(mImg_2);

        mImg_3 = rootView.findViewById(R.id.mImg_3);
        mImg_3.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(mImg_3);

        mImg_4 = rootView.findViewById(R.id.mImg_4);
        mImg_4.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(mImg_4);

        mImg_5 = rootView.findViewById(R.id.mImg_5);
        mImg_5.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(mImg_5);

        layout =rootView.findViewById(R.id.mScrollView);

        Toast.makeText(getContext(), "Please make sure you have active internet connection",
                Toast.LENGTH_LONG).show();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentImgViewViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Firebase.setAndroidContext(Objects.requireNonNull(getContext()));
        assert getArguments() != null;
        child_name = getArguments().getString("child_name");
        key_name = getArguments().getString("key_name");

        connectivityManager = (ConnectivityManager)
                Objects.requireNonNull(getActivity()).getSystemService(CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            readFirstLink();
            readSecondLink();
            readThirdLink();
            readFourthLink();
            readFifthLink();
        }else {
            Toast.makeText(getContext(),
                    "Please activate internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    public void readFirstLink(){
        //reading J or first link from firebase
        Firebase getJ = new Firebase(firebaseLink+"/"+child_name+"/"+key_name+"/"+"j");
        getJ.keepSynced(false);
        getJ.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                j = dataSnapshot.getValue(String.class);
                if (j == null||j.isEmpty()){
                    readFirstLink();
                }
                if (j.equalsIgnoreCase("m")){
                    mImg_1.setVisibility(View.INVISIBLE);
                }
                Picasso.get().load(j)
                        .placeholder(R.drawable.ic_menu_camera)
                        .resize(mImg_1.getWidth(),0)
                        .into(mImg_1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void readSecondLink(){
        //reading K or second link from Firebase
        Firebase getk = new Firebase(firebaseLink+"/"+child_name+"/"+key_name+"/"+"k");
        getk.keepSynced(false);
        getk.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                k = dataSnapshot.getValue(String.class);
                if (k == null||k.isEmpty()){
                    readSecondLink();
                }
                if (k.equalsIgnoreCase("m")){
                    mImg_2.setVisibility(View.INVISIBLE);
                }
                Picasso.get().load(k)
                        .placeholder(R.drawable.ic_menu_camera)
                        .resize(mImg_2.getWidth(),0)
                        .into(mImg_2);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void readThirdLink(){
        //reading l or third link
        Firebase getL = new Firebase(firebaseLink+"/"+child_name+"/"+key_name+"/"+"l");
        getL.keepSynced(false);
        getL.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                l = dataSnapshot.getValue(String.class);
                if (l == null||l.isEmpty()){
                    readThirdLink();
                }
                if (l.equalsIgnoreCase("m")){
                    mImg_3.setVisibility(View.INVISIBLE);
                }
                Picasso.get().load(l)
                        .placeholder(R.drawable.ic_menu_camera)
                        .resize(mImg_3.getWidth(),0)
                        .into(mImg_3);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void readFourthLink(){
        //reading m or fourth link
        Firebase getM = new Firebase(firebaseLink+"/"+child_name+"/"+key_name+"/"+"m");
        getM.keepSynced(false);
        getM.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m = dataSnapshot.getValue(String.class);
                if (m == null||m.isEmpty()){
                    readFourthLink();
                }
                if (m.equalsIgnoreCase("m")){
                    mImg_4.setVisibility(View.INVISIBLE);
                }
                Picasso.get().load(m)
                        .placeholder(R.drawable.ic_menu_camera)
                        .resize(mImg_4.getWidth(),0)
                        .into(mImg_4);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void readFifthLink(){
        //reading N or fifth link
        Firebase getN = new Firebase(firebaseLink+"/"+child_name+"/"+key_name+"/"+"n");
        getN.keepSynced(false);
        getN.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                n = dataSnapshot.getValue(String.class);
                if (n == null||n.isEmpty()){
                    readFifthLink();
                }
                if (n.equalsIgnoreCase("m")){
                    mImg_5.setVisibility(View.INVISIBLE);
                }
                Picasso.get().load(n)
                        .placeholder(R.drawable.ic_menu_camera)
                        .resize(mImg_5.getWidth(),0)
                        .into(mImg_5);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}

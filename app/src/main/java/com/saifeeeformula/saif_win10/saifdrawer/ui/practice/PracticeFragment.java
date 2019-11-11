package com.saifeeeformula.saif_win10.saifdrawer.ui.practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;


import com.facebook.ads.AudienceNetworkAds;
import com.saifeeeformula.saif_win10.saifdrawer.R;
import com.facebook.ads.*;

import java.util.Objects;

public class PracticeFragment extends Fragment implements View.OnClickListener {

    private PracticeViewModel practiceViewModel;
    Button mBtn_memorise, mBtn_mcq;
    private AdView adView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        practiceViewModel =
                ViewModelProviders.of(this).get(PracticeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        AudienceNetworkAds.initialize(Objects.requireNonNull(getContext()));
        adView = new AdView(getContext(),
                getResources().getString(R.string.facebook_rectangle_add),
                AdSize.RECTANGLE_HEIGHT_250);

        // Find the Ad Container
        LinearLayout adContainer = root.findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();

//        final TextView textView = root.findViewById(R.id.text_gallery);
        practiceViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        mBtn_memorise = root.findViewById(R.id.mBtn_memorise);
        mBtn_mcq = root.findViewById(R.id.mBtn_mcq);

        mBtn_mcq.setOnClickListener(this);
        mBtn_memorise.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        Bundle child_name;
        switch (v.getId()) {
            case R.id.mBtn_memorise:
                child_name = new Bundle();
                child_name.putString("child_name", "eeeMemoriseOne");
                Navigation.findNavController(v).navigate
                        (R.id.action_nav_gallery_to_memoriseRecView, child_name);
                break;
            case R.id.mBtn_mcq:
                child_name = new Bundle();
                child_name.putString("child_name", "eeeMcqOne");
                Navigation.findNavController(v).navigate
                        (R.id.action_nav_gallery_to_mcqRecView, child_name);
                break;
            default:
                Toast.makeText(getContext(), "Make sure you have active internet connection",
                        Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
package com.saifeeeformula.saif_win10.saifdrawer.ui.jobportal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class JobPortalFragment extends Fragment implements View.OnClickListener {

    private JobPortalViewModel slideshowViewModel;
    LinearLayout mLLeeeJob, mLLbankJob;
    private AdView adView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(JobPortalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

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

//        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        mLLeeeJob = root.findViewById(R.id.mLLeeeJob);
        mLLbankJob = root.findViewById(R.id.mLLbankJob);

        mLLeeeJob.setOnClickListener(this);
        mLLbankJob.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Bundle child_name;
        switch (v.getId()) {
            case R.id.mLLeeeJob:
                child_name = new Bundle();
                child_name.putString("child_name", "eeeJob");
                child_name.putString("actionBar_title", "Job Circular (EEE)");
                Navigation.findNavController(v).navigate(R.id.action_nav_slideshow_to_formulaView, child_name);
                break;
            case R.id.mLLbankJob:
                child_name = new Bundle();
                child_name.putString("child_name", "bankJob");
                child_name.putString("actionBar_title", "Job Circular (Bank)");
                Navigation.findNavController(v).navigate(R.id.action_nav_slideshow_to_formulaView, child_name);
                break;
            default:
                Toast.makeText(getContext(), "Please check your internet connection",
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
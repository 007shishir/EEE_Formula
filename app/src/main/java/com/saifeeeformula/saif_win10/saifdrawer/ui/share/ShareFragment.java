package com.saifeeeformula.saif_win10.saifdrawer.ui.share;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saifeeeformula.saif_win10.saifdrawer.R;

import java.util.Objects;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    private WebView mWebViewAboutUS;
    private Button mBtnFBgroup, mBtnFBpage;
    private String fb_GROUP_Url, fb_PAGE_Url, urlAboutUs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        fb_GROUP_Url = "https://www.facebook.com/groups/eee.video/";
        fb_PAGE_Url = "https://www.facebook.com/AVTutorial/";
        urlAboutUs = "file:///android_asset/index.html";

        mWebViewAboutUS = root.findViewById(R.id.mWebViewAboutUS);
        mWebViewAboutUS.getSettings().setBuiltInZoomControls(true);
        mWebViewAboutUS.clearCache(true);
        mWebViewAboutUS.clearHistory();
        mWebViewAboutUS.getSettings().setJavaScriptEnabled(true);
        mWebViewAboutUS.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebViewAboutUS.loadUrl(urlAboutUs);

        mBtnFBgroup = root.findViewById(R.id.mBtnFBgroup);
        mBtnFBpage = root.findViewById(R.id.mBtnFBpage);

        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        mBtnFBgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbIntent = newFacebookIntent(Objects.requireNonNull(getContext()).getPackageManager(),fb_GROUP_Url);
                startActivity(fbIntent);
            }
        });

        mBtnFBpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbIntent = newFacebookIntent(Objects.requireNonNull(getContext()).getPackageManager(),fb_PAGE_Url);
                startActivity(fbIntent);
            }
        });

        return root;
    }

    //This method is for opening facebook in app or in browser!
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
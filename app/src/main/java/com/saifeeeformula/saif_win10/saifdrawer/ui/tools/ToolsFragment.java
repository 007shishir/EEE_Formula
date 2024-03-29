package com.saifeeeformula.saif_win10.saifdrawer.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saifeeeformula.saif_win10.saifdrawer.R;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private WebView mWebPrivacy;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        String url = "file:///android_asset/privacy_policy.html";
        mWebPrivacy = root.findViewById(R.id.mWebPrivacy);
        mWebPrivacy.getSettings().setBuiltInZoomControls(true);

        mWebPrivacy.loadUrl(url);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
}
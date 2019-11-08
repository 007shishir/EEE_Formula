package com.saifeeeformula.saif_win10.saifdrawer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.saifeeeformula.saif_win10.saifdrawer.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView ch_1 = root.findViewById(R.id.btn01);
        final TextView ch_2 = root.findViewById(R.id.btn02);
        final TextView ch_3 = root.findViewById(R.id.btn03);
        final TextView ch_4 = root.findViewById(R.id.btn04);
        final TextView ch_5 = root.findViewById(R.id.btn05);
        final TextView ch_6 = root.findViewById(R.id.btn06);
        final TextView ch_7 = root.findViewById(R.id.btn07);
//        final TextView ch_8 = root.findViewById(R.id.btn08);
//        final TextView ch_9 = root.findViewById(R.id.btn09);
//        final TextView ch_10 = root.findViewById(R.id.btn10);
//        final TextView ch_11 = root.findViewById(R.id.btn11);
//        final TextView ch_12 = root.findViewById(R.id.btn12);
//        final TextView ch_13 = root.findViewById(R.id.btn13);
//        final TextView ch_14 = root.findViewById(R.id.btn14);
//        final TextView ch_15 = root.findViewById(R.id.btn15);

        ch_1.setOnClickListener(this);
        ch_2.setOnClickListener(this);
        ch_3.setOnClickListener(this);
        ch_4.setOnClickListener(this);
        ch_5.setOnClickListener(this);
        ch_6.setOnClickListener(this);
        ch_7.setOnClickListener(this);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    //basicEE elecMachines powerSys basicElec eeeMathCollection eeeMathCollection

    @Override
    public void onClick(View v) {
        Bundle child_name;
        switch (v.getId()) {
            case R.id.btn01:
                child_name = new Bundle();
                child_name.putString("child_name", "basicEE");
                child_name.putString("actionBar_title", "Basic Electrical Engineering");
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_formulaView, child_name);
                break;
            case R.id.btn02:
                child_name = new Bundle();
                child_name.putString("child_name", "elecMachines");
                child_name.putString("actionBar_title", "Electrical Machines");
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_formulaView, child_name);
                break;
            case R.id.btn03:
                child_name = new Bundle();
                child_name.putString("child_name", "powerSys");
                child_name.putString("actionBar_title", "Power System");
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_formulaView, child_name);
                break;
            case R.id.btn04:
                child_name = new Bundle();
                child_name.putString("child_name", "basicElec");
                child_name.putString("actionBar_title", "Basic Electronics");
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_formulaView, child_name);
                break;
            case R.id.btn05:
                child_name = new Bundle();
                child_name.putString("child_name", "eeeMathCollection");
                child_name.putString("actionBar_title", "EEE Math Collection");
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_formulaView, child_name);
                break;
            case R.id.btn06:
                child_name = new Bundle();
                child_name.putString("child_name", "eeeJobMath");
                child_name.putString("actionBar_title", "EEE Math for Job");
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_formulaView, child_name);
                break;
            case R.id.btn07:
                child_name = new Bundle();
                child_name.putString("child_name", "extraNote");
                child_name.putString("actionBar_title", "Extra Feature!");
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_formulaView, child_name);
                break;

            default:
                Toast.makeText(getContext(),
                        "Please make sure you have Active Internet Connection! ",
                        Toast.LENGTH_LONG).show();
        }
    }
}
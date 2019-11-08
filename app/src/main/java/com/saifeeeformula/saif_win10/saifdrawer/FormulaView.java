package com.saifeeeformula.saif_win10.saifdrawer;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import static android.content.Context.CONNECTIVITY_SERVICE;

public class FormulaView extends Fragment {
//recycler view and database referrence are here. The child name is
//came from previous fragment
    private RecyclerView mRecycler_Memorize;
    private DatabaseReference mDatabase;
    String childName_fromFragment, actionBarText;

    //To check internet connection
    private ConnectivityManager connectivityManager;

    private FormulaViewViewModel mViewModel;

    public static FormulaView newInstance() {
        return new FormulaView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.formula_view_fragment, container, false);

        connectivityManager = (ConnectivityManager) Objects.
                requireNonNull(getActivity()).getSystemService(CONNECTIVITY_SERVICE);

        assert getArguments() != null;
        childName_fromFragment = getArguments().getString("child_name");
        actionBarText = getArguments().getString("actionBar_title");
        mDatabase = FirebaseDatabase.getInstance().getReference().child(childName_fromFragment);
        mDatabase.keepSynced(false);
        mRecycler_Memorize = root.findViewById(R.id.mRecycler_Memorize);
        mRecycler_Memorize.setHasFixedSize(true);
        mRecycler_Memorize.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FormulaViewViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Parameter, ParameterViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Parameter, ParameterViewHolder>
                        (Parameter.class, R.layout.formula_recycler_view_list, ParameterViewHolder.class, mDatabase) {
                    @Override
                    protected void populateViewHolder(ParameterViewHolder viewHolder, Parameter model, int position) {
                        final String post_key = getRef(position).getKey();
                        viewHolder.setSource(model.getSource());
                        viewHolder.setTopic(model.getTopic());
                        viewHolder.setSum(model.getSum());
                        viewHolder.setTotal(model.getTotal());
                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                                if (networkInfo != null && networkInfo.isConnected()){
                                    Bundle stringData = new Bundle();
                                    stringData.putString("child_name", childName_fromFragment);
                                    stringData.putString("key_name", post_key);
                                    stringData.putString("actionBar_title", actionBarText);
                                    Navigation.findNavController(v).navigate(R.id.action_formulaView_to_fragmentImgView,
                                            stringData);
                                }else {
                                    Snackbar.make(Objects.requireNonNull(getActivity()).
                                            findViewById(R.id.drawer_layout), "No Network Connection...",
                                            Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                };
        mRecycler_Memorize.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ParameterViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public ParameterViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }
        public void setSource(String source)
        {
            TextView post_source = mView.findViewById(R.id.mTxt_source);
            post_source.setText(source);
        }
        public void setTopic(String topic)
        {
            TextView post_topic = mView.findViewById(R.id.mTxt_topic);
            post_topic.setText(topic);
        }
        public void setSum(String sum)
        {
            TextView post_sum = mView.findViewById(R.id.mTxt_sum);
            post_sum.setText(sum);
        }
        public void setTotal(String total)
        {
            TextView post_total = mView.findViewById(R.id.mTxt_total);
            post_total.setText(total);
        }
    }

    //To change the action bar title
    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity) Objects.requireNonNull(getActivity())).setActionBarTitle(actionBarText);
    }
}

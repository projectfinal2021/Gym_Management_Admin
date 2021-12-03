package com.example.gymmanagementadmin.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gymmanagementadmin.adapter.DietChartAdapter;
import com.example.gymmanagementadmin.databinding.FragmentDietChartBinding;
import com.example.gymmanagementadmin.model.DietChartInfo;
import com.example.gymmanagementadmin.ui.AddDietActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DietChartFragment extends Fragment {
    private static final String TAG = "DietChartFragment";
    private FragmentDietChartBinding fragmentDietChartBinding;
    private DatabaseReference databaseReference;
    private DietChartAdapter dietChartAdapter;
    ArrayList<DietChartInfo> dietChartInfos;
    ProgressDialog Dialog;

    public DietChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDietChartBinding = FragmentDietChartBinding.inflate(inflater, container, false);
        View view = fragmentDietChartBinding.getRoot();

        fragmentDietChartBinding.textviewDietChartFragHeader.setText("Diet Chart");

        Dialog = new ProgressDialog(getActivity());

        databaseReference = FirebaseDatabase.getInstance().getReference("Diet Chart");
        dietChartInfos = new ArrayList<>();
        dietChartAdapter = new DietChartAdapter(dietChartInfos);
        fragmentDietChartBinding.recyclerviewDietChartFragDietChart.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragmentDietChartBinding.recyclerviewDietChartFragDietChart.setAdapter(dietChartAdapter);


        fragmentDietChartBinding.flotingButtonDietChartFragAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddDietActivity.class));
            }
        });


        Log.d(TAG, "onCreateView: ");

        return view;
    }

    private void getDietChartList() {
        Log.d(TAG, "getDietChartList: ");
        Dialog.setMessage("Please wait ...");
        Dialog.show();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dietChartInfos.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    DietChartInfo dietChartInfo = snapshot1.getValue(DietChartInfo.class);
                    Log.d(TAG, "DietInfo:" + snapshot1.getValue(DietChartInfo.class));
                    dietChartInfos.add(dietChartInfo);

                }
                fragmentDietChartBinding.flotingButtonDietChartFragAdd.setVisibility(View.VISIBLE);
                dietChartAdapter.notifyDataSetChanged();
                Dialog.dismiss();
                

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Dialog.dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        getDietChartList();
    }
}

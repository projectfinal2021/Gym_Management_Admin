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

import com.example.gymmanagementadmin.ui.AddRoutineActivity;
import com.example.gymmanagementadmin.adapter.ExerciseAdapter;
import com.example.gymmanagementadmin.databinding.FragmentRoutineBinding;
import com.example.gymmanagementadmin.model.ExerciseInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoutineFragment extends Fragment {
    private static final String TAG = "RoutineFragment";
    private FragmentRoutineBinding fragmentRoutineBinding;
    ;
    private DatabaseReference databaseReference;
    private ExerciseAdapter exerciseAdapter;
    ArrayList<ExerciseInfo> exerciseInfos;
    ProgressDialog Dialog;

    public RoutineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRoutineBinding = FragmentRoutineBinding.inflate(inflater, container, false);
        View view = fragmentRoutineBinding.getRoot();

        fragmentRoutineBinding.textviewRoutineFragHeader.setText("Routine");
        Dialog = new ProgressDialog(getActivity());

        databaseReference = FirebaseDatabase.getInstance().getReference("Exercises");
        exerciseInfos = new ArrayList<>();
        exerciseAdapter = new ExerciseAdapter(exerciseInfos);
        fragmentRoutineBinding.recyclerviewRoutineFragRoutine.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragmentRoutineBinding.recyclerviewRoutineFragRoutine.setAdapter(exerciseAdapter);


        fragmentRoutineBinding.flotingButtonRoutineFragAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddRoutineActivity.class));
            }
        });
        return view;
    }

    private void getRoutineList() {
        Log.d(TAG, "getRoutineList: ");
        Dialog.setMessage("Please wait ...");
        Dialog.show();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                exerciseInfos.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    ExerciseInfo exerciseInfo = snapshot1.getValue(ExerciseInfo.class);
                    Log.d(TAG, "ExerciseInfo:" + snapshot1.getValue(ExerciseInfo.class));
                    exerciseInfos.add(exerciseInfo);

                }
                fragmentRoutineBinding.flotingButtonRoutineFragAdd.setVisibility(View.VISIBLE);
                exerciseAdapter.notifyDataSetChanged();
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
        getRoutineList();
    }
}
package com.example.gymmanagementadmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.gymmanagementadmin.KEYS;
import com.example.gymmanagementadmin.Tools;
import com.example.gymmanagementadmin.databinding.FragmentRoutineBinding;

public class RoutineFragment extends Fragment {
    private static final String TAG = "RoutineFragment";
    private FragmentRoutineBinding fragmentRoutineBinding;

    public RoutineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRoutineBinding = FragmentRoutineBinding.inflate(inflater, container, false);
        View view = fragmentRoutineBinding.getRoot();

        fragmentRoutineBinding.textviewRoutineFragHeader.setText("Routine: " + Tools.getPref(KEYS.BODY_TYPE, null));

        return view;
    }
}
package com.example.gymmanagementadmin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.gymmanagementadmin.R;
import com.example.gymmanagementadmin.databinding.ActivityAddDietBinding;
import com.google.firebase.database.DatabaseReference;

public class AddRoutineActivity extends AppCompatActivity {
    private static final String TAG = "ActivityAddDiet";
    private ActivityAddDietBinding activityAddDietBinding;
    private String dietChart, bodyTypes, time;
    private DatabaseReference databaseReference;
    ProgressDialog Dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);
    }
}
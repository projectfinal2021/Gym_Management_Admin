package com.example.gymmanagementadmin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.gymmanagementadmin.R;

import com.example.gymmanagementadmin.databinding.ActivityAddRoutineBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRoutineActivity extends AppCompatActivity {
    private static final String TAG = "ActivityAddDiet";
    private ActivityAddRoutineBinding activityAddRoutineBinding;
    private String dietChart, bodyTypes, time;
    private DatabaseReference databaseReference;
    ProgressDialog Dialog;
    int status_pos = 0;
    int status_pos2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddRoutineBinding = ActivityAddRoutineBinding.inflate(getLayoutInflater());
        setContentView(activityAddRoutineBinding.getRoot());

        spinnerbodyTypes(status_pos, status_pos2);

        databaseReference = FirebaseDatabase.getInstance().getReference("Diet Chart");
        Dialog = new ProgressDialog(AddRoutineActivity.this);

    }

    private void spinnerbodyTypes(int status_pos, int status_pos2) {
        ArrayAdapter<String> hospital_typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item
                , getResources().getStringArray(R.array.body_types));
        ArrayAdapter<String> hospital_typeAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item
                , getResources().getStringArray(R.array.total_days));
        hospital_typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hospital_typeAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAddRoutineBinding.spinnerAddRoutineActBodyTypes.setAdapter(hospital_typeAdapter);
        activityAddRoutineBinding.spinnerAddRoutineActTotalDays.setAdapter(hospital_typeAdapter2);
    }


}
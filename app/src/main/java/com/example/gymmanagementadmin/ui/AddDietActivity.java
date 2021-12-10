package com.example.gymmanagementadmin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.gymmanagementadmin.R;
import com.example.gymmanagementadmin.databinding.ActivityAddDietBinding;
import com.example.gymmanagementadmin.model.DietChartInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDietActivity extends AppCompatActivity {
    private static final String TAG = "ActivityAddDiet";
    private ActivityAddDietBinding activityAddDietBinding;
    private String dietChart, bodyTypes, time;
    private DatabaseReference databaseReference;
    ProgressDialog Dialog;
    int status_pos = 0;
    String updateKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddDietBinding = ActivityAddDietBinding.inflate(getLayoutInflater());
        setContentView(activityAddDietBinding.getRoot());


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            activityAddDietBinding.textviewAddDietActHeader.setText("Edit Diet");
            activityAddDietBinding.buttonAddDietActAdd.setText("EDIT DIET");
            activityAddDietBinding.edittextAddDietActDietChartName.setText(
                    bundle.getString("dietChartName"));
            activityAddDietBinding.editTextAddDietActTime.setText(
                    bundle.getString("dietChartTime"));
            updateKey = bundle.getString("dietKey");
            //Spinner
            switch (bundle.getString("bodyType")) {

                case "Ectomorph":
                    status_pos = 1;
                    break;
                case "Mesomorph":
                    status_pos = 2;
                    break;
                case "Endomorph":
                    status_pos = 3;
                    break;

            }
            spinnerbodyTypes(status_pos);
        } else {
            //Spinner
            spinnerbodyTypes(status_pos);

        }


        databaseReference = FirebaseDatabase.getInstance().getReference("Diet Chart");
        Dialog = new ProgressDialog(AddDietActivity.this);

        activityAddDietBinding.buttonAddDietActAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addDietValidation()) {
                    addDiet();
                } else {
                    Toast.makeText(AddDietActivity.this, "Please fill the all Information. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addDiet() {
        Log.d(TAG, "addDiet: ");
        Dialog.setMessage("Please wait ...");
        Dialog.show();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (status_pos != 0) {
                    DietChartInfo dietChartInfo = new DietChartInfo(bodyTypes, dietChart, time, updateKey);
                    databaseReference.child(updateKey).setValue(dietChartInfo);
                    Dialog.dismiss();
                    finish();
                } else {
                    String key = databaseReference.push().getKey();
                    DietChartInfo dietChartInfo = new DietChartInfo(bodyTypes, dietChart, time, key);
                    databaseReference.child(key).setValue(dietChartInfo);
                    Dialog.dismiss();
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Dialog.dismiss();
            }
        });
    }

    private boolean addDietValidation() {
        dietChart = activityAddDietBinding.edittextAddDietActDietChartName.getText().toString();
        bodyTypes = activityAddDietBinding.spinnerAddDietActBodyTypes.getSelectedItem().toString();
        time = activityAddDietBinding.editTextAddDietActTime.getText().toString();


        if (dietChart.isEmpty() || time.isEmpty()
                || bodyTypes.equals("Select one")) {
            Log.d(TAG, "userRegistrationValidation: false");
            return false;
        } else {
            Log.d(TAG, "userRegistrationValidation: true");
            return true;
        }
    }

    private void spinnerbodyTypes(int status_pos) {
        ArrayAdapter<String> hospital_typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item
                , getResources().getStringArray(R.array.body_types));
        hospital_typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAddDietBinding.spinnerAddDietActBodyTypes.setAdapter(hospital_typeAdapter);
        activityAddDietBinding.spinnerAddDietActBodyTypes.setSelection(status_pos);

    }
}
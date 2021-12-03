package com.example.gymmanagementadmin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.gymmanagementadmin.KEYS;
import com.example.gymmanagementadmin.R;
import com.example.gymmanagementadmin.Tools;
import com.example.gymmanagementadmin.databinding.ActivityLoginBinding;
import com.example.gymmanagementadmin.model.AdminsInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "ActivityLogin";
    private ActivityLoginBinding activityLoginBinding;
    private DatabaseReference databaseReference;
    private String userPhoneNumber, userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());

        databaseReference = FirebaseDatabase.getInstance().getReference("Admins");

        activityLoginBinding.buttonLoginActLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityLoginBinding.edittextLoginActMobileNumber.length() != 11 || activityLoginBinding.edittextLoginActMobileNumber.equals("")
                        || activityLoginBinding.edittextLoginActPassword.equals("")) {
                    Toast.makeText(LoginActivity.this, "Invalid Phone Number or Empty Password filled.", Toast.LENGTH_SHORT).show();
                } else {
                    userPhoneNumber = "+88" + activityLoginBinding.edittextLoginActMobileNumber.getText().toString().trim();
                    userPassword = activityLoginBinding.edittextLoginActPassword.getText().toString().trim();

                    checkUser(userPhoneNumber, userPassword);
                }
            }
        });

        activityLoginBinding.edittextLoginActPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (activityLoginBinding.edittextLoginActPassword.getRight() -
                            activityLoginBinding.edittextLoginActPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if (activityLoginBinding.edittextLoginActPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                            activityLoginBinding.edittextLoginActPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            activityLoginBinding.edittextLoginActPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_padlock, 0, R.drawable.ic_eye_private, 0);
                        } else {
                            activityLoginBinding.edittextLoginActPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            activityLoginBinding.edittextLoginActPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_padlock, 0, R.drawable.ic_eye_public, 0);
                        }
                        return true;
                    }

                }
                return false;
            }
        });
    }

    private void checkUser(String userPhoneNumber, String userPassword) {
        Log.d(TAG, "phone no:" + userPhoneNumber);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    AdminsInfo adminsInfo = snapshot1.getValue(AdminsInfo.class);
                    Log.d(TAG, "adminInfo:" + snapshot1.getValue(AdminsInfo.class));
                    if (adminsInfo.getUserPhoneNumber().equals(userPhoneNumber) && adminsInfo.getUserPassword().equals(userPassword)) {
                        Tools.savePref(KEYS.FULL_NAME, "" + adminsInfo.getUserFullName());
                        Tools.savePref(KEYS.PHONE_NO, "" + adminsInfo.getUserPhoneNumber());
                        Tools.savePref(KEYS.IMAGE_URI, "" + adminsInfo.getUserImageLink());
                        Tools.savePrefBoolean(KEYS.IS_LOGGED_IN, true);
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong Credential.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
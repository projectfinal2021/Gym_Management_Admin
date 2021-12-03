package com.example.gymmanagementadmin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gymmanagementadmin.KEYS;
import com.example.gymmanagementadmin.R;
import com.example.gymmanagementadmin.Tools;
import com.example.gymmanagementadmin.databinding.ActivityHomeBinding;
import com.example.gymmanagementadmin.databinding.NavHeaderLayoutBinding;
import com.example.gymmanagementadmin.fragment.DietChartFragment;
import com.example.gymmanagementadmin.fragment.RoutineFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener{
    private static final String TAG = "ActivityHome";
    private ActivityHomeBinding activityHomeBinding;
    private NavHeaderLayoutBinding navHeaderLayoutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, activityHomeBinding.layoutHomeActDrawerLayout,
                activityHomeBinding.toolbarHomeActTop,
                R.string.openNavDrawer, R.string.closeNavDrawer);
        activityHomeBinding.layoutHomeActDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navHeaderLayoutBinding = NavHeaderLayoutBinding.bind(activityHomeBinding.navHomeActNavBar.getHeaderView(0));

        getHeaderInformation(Tools.getPref(KEYS.PHONE_NO, null));

        activityHomeBinding.navHomeActNavBar.setNavigationItemSelectedListener(this);
        activityHomeBinding.bottomNavHomeActBottomNavView1.setOnItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_HomeAct_container, new RoutineFragment()).commit();

    }

    private void getHeaderInformation(String phone_no) {

        navHeaderLayoutBinding.textviewNavHeaderLayoutText.setText("" + Tools.getPref(KEYS.FULL_NAME,null));
        navHeaderLayoutBinding.textviewNavHeaderLayoutText.setText("" + Tools.getPref(KEYS.PHONE_NO,null));
        Picasso.get().load(Tools.getPref(KEYS.IMAGE_URI,null)).into(navHeaderLayoutBinding.imageviewNavHeaderLayoutImage);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "Navigation item selected. ");

        switch (item.getItemId()) {
            case R.id.bottom_nav_diet_chart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_HomeAct_container, new DietChartFragment()).commit();
                break;
            case R.id.bottom_nav_routine:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_HomeAct_container, new RoutineFragment()).commit();
                break;
            case R.id.nav_setting:
               // startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                break;
            case R.id.nav_rate_me:
                Toast.makeText(HomeActivity.this, "Rate the app", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_term_condition:
                Toast.makeText(HomeActivity.this, "Term and Condition", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                logout();
                break;


        }
        activityHomeBinding.layoutHomeActDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //logout current user
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Tools.savePrefBoolean(KEYS.IS_LOGGED_IN, false);
        finish();
    }
}
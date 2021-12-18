package com.appforall.justintimefinance;
import com.appforall.justintimefinance.MenuActions.Registration.*;
import com.appforall.justintimefinance.MenuActions.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout menuLayout;
    public ActionBarDrawerToggle actionBarToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuLayout = findViewById(R.id.drawer_layout);
        actionBarToggle = new ActionBarDrawerToggle(this, menuLayout, R.string.nav_open, R.string.nav_close);



        menuLayout.addDrawerListener(actionBarToggle);
        actionBarToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationDrawer();

    }

    private void setNavigationDrawer() {
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int id = item.getItemId();
                if (id == R.id.menu_my_account) {
//                   h Intent intnt = new Intent(getApplicationContext(), MyAccountActivity.class); //move from this activity to mainactivity
////                    startActivity(intnt); //start te new activity
                    fragment = new MyAccount();
                } else if (id == R.id.menu_fund_transfer) {
                    fragment = new FundTransfer(); // checking for different fragments
                } else if (id == R.id.menu_transactions) {
                    fragment = new Transactions(); // checking for different fragments
                } else if (id == R.id.menu_activate_card) {
                    fragment = new RegisterCard(); // checking for different fragments
                } else if (id == R.id.logout) {
                    Intent intnt = new Intent(getApplicationContext(), Login.class); //move from this activity to mainactivity
                    startActivity(intnt); //start the new activity
                }

                if (fragment != null)
                    startFragment(fragment);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void startFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, fragment); //for start of new activity
            transaction.commit();
            menuLayout.closeDrawers();
        }
    }
}
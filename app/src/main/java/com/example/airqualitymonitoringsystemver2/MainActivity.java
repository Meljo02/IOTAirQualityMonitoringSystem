package com.example.airqualitymonitoringsystemver2;package;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ledStatusRef = database.getReference("ledStatus");

        toolBar.findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        drawerLayout.findViewById(R.id.drawer_layout);
        navigationView.findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolBar,
                R.string.Open,
                R.string.Close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.lavander_dark));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Home()).commit();
            navigationView.setCheckedItem(R.id.Home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.Home){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Home()).commit();
        }else if (menuItem.getItemId() == R.id.Map){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Map()).commit();
        }drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
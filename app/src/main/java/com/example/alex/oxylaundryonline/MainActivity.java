package com.example.alex.oxylaundryonline;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        BottomNavigationView btmNavigation = findViewById(R.id.navigation);
        btmNavigation.setOnNavigationItemSelectedListener(this);
        NavigationView drawNav = findViewById(R.id.nav_view);
        drawNav.setNavigationItemSelectedListener(this);
        loadFragment(new PromoFragment());



        mDatabase.child(auth.getCurrentUser().getUid()).child("nama").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);
                TextView navEmail = (TextView) headerView.findViewById(R.id.tv_email);
                navEmail.setText(auth.getCurrentUser().getEmail());
                TextView navUser = (TextView) headerView.findViewById(R.id.tv_user);
                navUser.setText(dataSnapshot.getValue(String.class));
                //Toast.makeText(MainActivity.this, dataSnapshot.getValue(String.class), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(android.support.v4.app.Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
//        else if (fragment.equals(new SettingsFragment())){
//            getSupportFragmentManager().beginTransaction().replace(R.id.drawer, fragment).commit();
//            return true;
//
//        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        android.support.v4.app.Fragment fragment = null;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);

        switch (item.getItemId()){
            case R.id.navigation_promo:
                fragment = new PromoFragment();
                loadFragment(fragment);
            break;
            case R.id.navigation_jadwal:
                fragment = new JadwalFragment();
                loadFragment(fragment);
            break;
            case R.id.navigation_aktivitas:
                fragment = new AktivitasFragment();
                loadFragment(fragment);
            break;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_user_info:
                startActivity(new Intent(MainActivity.this, UserInfo.class));
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

}

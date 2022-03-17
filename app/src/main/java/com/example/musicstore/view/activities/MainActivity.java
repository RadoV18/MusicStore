package com.example.musicstore.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.musicstore.R;
import com.example.musicstore.view.fragments.HomeFragment;
import com.example.musicstore.view.fragments.LoginFragment;
import com.example.musicstore.view.fragments.SignUpFragment;
import com.google.android.material.navigation.NavigationView;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment())
                    .commit();
        }

        try {
            // customer logged in
            String name = getIntent().getExtras().getString("username");
            String message = "Bienvenido, " + name + ".";
            TextView tvUsername = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_tvUsername);
            tvUsername.setText(message);
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.customer_menu);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // admin logged in
            String adminName = getIntent().getExtras().getString("adminName");
            String message = "Bienvenido, " + adminName + ".";
            TextView tvUsername = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_tvUsername);
            tvUsername.setText(message);
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.admin_menu);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_log_out:
                Intent invokeMain = new Intent(MainActivity.this, MainActivity.class);
                startActivity(invokeMain);
                break;
            case R.id.nav_home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new HomeFragment())
                        .commit();
                break;

            case R.id.nav_login:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new LoginFragment())
                        .commit();
                break;
            case R.id.nav_sign_up:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new SignUpFragment())
                        .commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
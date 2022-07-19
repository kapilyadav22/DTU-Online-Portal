package com.example.dtuadminkapil.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dtuadminkapil.R;
import com.example.dtuadminkapil.User.ebook.EbookActivity;
import com.example.dtuadminkapil.User.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class UserloginActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
   private NavigationView navigationView;


    private static final int TIME_LIMIT = 2000;
    private static long backPressed = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);


        bottomNavigationView = findViewById(R.id.bottomnavigationview);
        navController = Navigation.findNavController(this,R.id.frame_layout);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.start,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Intent intent = new Intent(UserloginActivity.this, UserActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slideinleft,R.anim.slideoutright);
            finish();
        }
        backPressed = System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        } return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_developer:
                Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_video:
                Toast.makeText(this, "Video Lectures", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_ebook:
                startActivity(new Intent(this, EbookActivity.class));
                Toast.makeText(this, "Ebooks", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_theme:

                break;
            case R.id.navigation_website:
                Toast.makeText(this, "Website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
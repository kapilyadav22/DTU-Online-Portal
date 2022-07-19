package com.example.dtuadminkapil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.dtuadminkapil.ADMIN.AdminLoginActivity;
import com.example.dtuadminkapil.User.UserActivity;

public class MainActivity extends AppCompatActivity {
        private Button adminbutton,userbutton;
    private static final int TIME_LIMIT = 2000;
    private static long backPressed = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //enable full screen

        setContentView(R.layout.activity_main);
        adminbutton = findViewById(R.id.adminbutton);
        userbutton = findViewById(R.id.userbutton);

        adminbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
            }
        });

        userbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
            }
        });

    }
    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast backtoast = Toast.makeText(getApplicationContext(), "PRESS AGAIN TO EXIT", Toast.LENGTH_SHORT);
            backtoast.show();
            finish();
        }
        backPressed = System.currentTimeMillis();
    }
}
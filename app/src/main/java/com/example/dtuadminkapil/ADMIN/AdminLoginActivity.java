package com.example.dtuadminkapil.ADMIN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dtuadminkapil.HomeActivity;
import com.example.dtuadminkapil.MainActivity;
import com.example.dtuadminkapil.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText useremail,userpassword;
    private Button loginbutton1;
    private static final int TIME_LIMIT = 2000;
    private static long backPressed = 0;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        useremail = (EditText) findViewById(R.id.admin_email);
        loginbutton1 = findViewById(R.id.loginbutton);
        userpassword = (EditText) findViewById(R.id.admin_Password);

        progressBar = (ProgressBar) findViewById(R.id.prgs1);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(AdminLoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }


        loginbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    adminlogin();
            }
        });
    }

    private void adminlogin() {
        String email = useremail.getText().toString().trim();
        String password = userpassword.getText().toString().trim();

        if(email.isEmpty()){
            useremail.setError("Email is required");
            useremail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            useremail.setError("Please Enter a valid Email!!!");
            useremail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            userpassword.setError("Password is required");
            userpassword.requestFocus();
            return;
        }
        if(password.length()<8)
        {
            userpassword.setError("Min Password Length is 8 characters");
            userpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(AdminLoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                    progressBar.setVisibility(View.GONE);
                    finish();
                }
                else{
                    Toast.makeText(AdminLoginActivity.this, "Failed to login!Please check your credentials", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }
    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Intent intent = new Intent(AdminLoginActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slideinleft,R.anim.slideoutright);
            finish();
        }
        backPressed = System.currentTimeMillis();
    }


}
package com.example.dtuadminkapil.User;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dtuadminkapil.ADMIN.AdminLoginActivity;
import com.example.dtuadminkapil.HomeActivity;
import com.example.dtuadminkapil.MainActivity;
import com.example.dtuadminkapil.R;
import com.example.dtuadminkapil.notice.UploadActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button userloginbutton;
    private EditText useremail,userpassword;
    private static final int TIME_LIMIT = 2000;
    private static long backPressed = 0;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dtu-admin-981bd-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth = FirebaseAuth.getInstance();



        textView = findViewById(R.id.signup);
        textView.setOnClickListener(this);
        useremail = (EditText) findViewById(R.id.user_email);
        userpassword = (EditText) findViewById(R.id.user_Password);
        userloginbutton = findViewById(R.id.userloginbutton);
        userloginbutton.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.prgsloginuser);

    }

    private void userlogin() {
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
        String firebaseemail = email.replace('.','^');

      reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              if(snapshot.hasChild(firebaseemail)) {
                  final String getpassword = snapshot.child(firebaseemail).child("password").getValue(String.class);

                  if(getpassword.equals(password)){
                      Toast.makeText(UserActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(UserActivity.this, UserloginActivity.class);
                      startActivity(intent);
                      overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                      progressBar.setVisibility(View.GONE);
                  }
                  else
                  {
                      Toast.makeText(UserActivity.this, "Failed to login!Please check your credentials", Toast.LENGTH_SHORT).show();
                      progressBar.setVisibility(View.GONE);
                  }
              }
              else {
                  Toast.makeText(UserActivity.this, "User Not Found!Please check your credentials", Toast.LENGTH_SHORT).show();
                  progressBar.setVisibility(View.GONE);
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

    }
    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slideinleft,R.anim.slideoutright);
            finish();
        }
        backPressed = System.currentTimeMillis();
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.signup:
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(UserActivity.this, SignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                break;
            case R.id.userloginbutton:  userlogin();
                break;
            default:  break;
        }
    }

}
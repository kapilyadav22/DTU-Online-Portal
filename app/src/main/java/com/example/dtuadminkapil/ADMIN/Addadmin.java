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
import com.example.dtuadminkapil.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Addadmin extends AppCompatActivity{
   private EditText newadminname,newadminage,newadminemail,newadminpassword;
   private Button button;
   private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadmin);
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth =FirebaseAuth.getInstance();
        newadminname = findViewById(R.id.newadminname);
        newadminage = findViewById(R.id.newadminage);
        newadminemail = findViewById(R.id.newadminemail);
        newadminpassword = findViewById(R.id.newadminpassword);
        button = findViewById(R.id.addadminbutton);
        progressBar = findViewById(R.id.prgsbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeradmin();
            }
        });
    }

    private void registeradmin() {
        String emailid = newadminemail.getText().toString().trim();
        String password = newadminpassword.getText().toString().trim();
        String fullname = newadminname.getText().toString().trim();
        String age = newadminage.getText().toString().trim();


        if(emailid.isEmpty()){
            newadminemail.setError("Email is required");
            newadminemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()){
            newadminemail.setError("Please Enter a valid Email!!!");
            newadminemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            newadminpassword.setError("Password is required");
            newadminpassword.requestFocus();
            return;
        }
        if(password.length()<8)
        {   newadminpassword.setError("Min Password Length is 8 characters");
            newadminpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Admin admin = new Admin(fullname,age,emailid,password);
                    String firebaseemail = emailid.replace('.','^');

                    FirebaseDatabase.getInstance().getReference("users").child(firebaseemail).setValue(admin);

                    FirebaseDatabase.getInstance().getReference("Admin").child(firebaseemail).setValue(admin)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Addadmin.this, "Admin Has been Added", Toast.LENGTH_SHORT).show();;
                                        progressBar.setVisibility(View.GONE);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Addadmin.this, "Failed to register Admin! Try Again!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                }
                else{
                    Toast.makeText(Addadmin.this, "Failed to register Admin! Try Again!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}

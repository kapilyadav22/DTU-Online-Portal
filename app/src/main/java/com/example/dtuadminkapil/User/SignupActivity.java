package com.example.dtuadminkapil.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dtuadminkapil.ADMIN.Addadmin;
import com.example.dtuadminkapil.ADMIN.Admin;
import com.example.dtuadminkapil.R;
import com.example.dtuadminkapil.notice.Noticedata;
import com.example.dtuadminkapil.notice.UploadActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.ref.Reference;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {
    private EditText newusername, newuserage, newuseremail, newuserpassword;
    private Button usersignbutton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dtu-admin-981bd-default-rtdb.firebaseio.com/");
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        newusername = findViewById(R.id.newusername);
        newuserage = findViewById(R.id.newuserage);
        newuseremail = findViewById(R.id.newuseremail);
        newuserpassword = findViewById(R.id.newuserpassword);
        progressBar = findViewById(R.id.usersignupprgs);
        usersignbutton = findViewById(R.id.usersignupbutton);

        usersignbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
            }
        });

    }

    private void registeruser() {
        String emailid = newuseremail.getText().toString().trim();
        String password = newuserpassword.getText().toString().trim();
        String fullname = newusername.getText().toString().trim();
        String age = newuserage.getText().toString().trim();


        if (emailid.isEmpty()) {
            newuseremail.setError("Email is required");
            newuseremail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            newuseremail.setError("Please Enter a valid Email!!!");
            newuseremail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            newuserpassword.setError("Password is required");
            newuserpassword.requestFocus();
            return;
        }
        if (password.length() < 8) {
            newuserpassword.setError("Min Password Length is 8 characters");
            newuserpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = new User(fullname,age,emailid,password);
                //., $, #, [, ], /, are not allowed in firebase path, so replace .from email to ^
                // we will replace with that character which is invalid as an emailaddress,
                //because if it will be a valid email character, then it can collide with the path
                //like kapil.yadavgfg@gmail.com replaces with and invalid email character so that,
                //it will never occur again in an email address
                String firebaseemail = emailid.replace('.','^');
                reference.child("users").child(firebaseemail).setValue(user);


                    Toast.makeText(SignupActivity.this, "User Has been Registered", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    finish();

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



       /* mAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(fullname,age,emailid,password);
                    FirebaseDatabase.getInstance().getReference("Users").
                            child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this, "User Has been Registered", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(SignupActivity.this, "Failed to register User! Try Again!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                }
                else{
                    Toast.makeText(SignupActivity.this, "Failed to register User! Try Again!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    */
        }
    }


package com.example.dtuadminkapil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dtuadminkapil.ADMIN.Addadmin;
import com.example.dtuadminkapil.ADMIN.AdminLoginActivity;
import com.example.dtuadminkapil.ADMIN.Uploadpdf;
import com.example.dtuadminkapil.User.UserActivity;
import com.example.dtuadminkapil.faculty.Updatefaculty;
import com.example.dtuadminkapil.notice.Deletenoticeactivity;
import com.example.dtuadminkapil.notice.UploadActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener{
   private CardView uploadnotice,uploadimage,uploadpdf,updatefaculty,deletenotice,addmoreadmin;
   private Button logoutbutton;
    private static final int TIME_LIMIT = 2000;
    private static long backPressed = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        uploadnotice = findViewById(R.id.Uploadnotice);
        uploadimage = findViewById(R.id.Uploadimage);
        uploadpdf = findViewById(R.id.UploadEbook);
        updatefaculty = findViewById(R.id.Updatefaculty);
        deletenotice = findViewById(R.id.Deletenotice);
        addmoreadmin = findViewById(R.id.Addmoreadmin);
        logoutbutton = findViewById(R.id.logoutbutton);
        //add clicklistener to cards
        uploadnotice.setOnClickListener(this);
        uploadimage.setOnClickListener(this);
        uploadpdf.setOnClickListener(this);
        updatefaculty.setOnClickListener(this);
        deletenotice.setOnClickListener(this);
        addmoreadmin.setOnClickListener(this);
        logoutbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {   Intent intent;
        switch(v.getId())
        {case R.id. logoutbutton:
            FirebaseAuth.getInstance().signOut();
            intent = new Intent(HomeActivity.this, AdminLoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
            finish();
                  break;
            case R.id.Uploadnotice:
                intent = new Intent(HomeActivity.this, UploadActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                break;
            case R.id.Uploadimage:
                intent = new Intent(HomeActivity.this, UploadActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                break;
            case R.id.UploadEbook:
                intent = new Intent(HomeActivity.this, Uploadpdf.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                break;
            case R.id.Updatefaculty:
                intent = new Intent(HomeActivity.this, Updatefaculty.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                break;
            case R.id.Deletenotice:
                intent = new Intent(HomeActivity.this, Deletenoticeactivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                break;
            case R.id.Addmoreadmin:
                intent = new Intent(HomeActivity.this, Addadmin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                break;
            default:  break;
        }
    }
    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();

        } else {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slideinleft,R.anim.slideoutright);
            finish();
        }

        backPressed = System.currentTimeMillis();
    }
}
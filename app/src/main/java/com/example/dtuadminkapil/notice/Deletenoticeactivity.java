package com.example.dtuadminkapil.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dtuadminkapil.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Deletenoticeactivity extends AppCompatActivity {

    private RecyclerView deletenoticerecyler;
    private ProgressBar progressBar;
    private ArrayList<Noticedata> list;
    private NoticeAdapter adapter;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletenoticeactivity);

        deletenoticerecyler = findViewById(R.id.deletenoticerecycler);
        progressBar = findViewById(R.id.progressbar);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");

        deletenoticerecyler.setLayoutManager(new LinearLayoutManager(this));
        deletenoticerecyler.setHasFixedSize(true);
        getNotice();

    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snapshot6: snapshot.getChildren()){
                    Noticedata data = snapshot6.getValue(Noticedata.class);
                    list.add(data);
                }
                
                adapter = new NoticeAdapter(Deletenoticeactivity.this,list);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                deletenoticerecyler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Deletenoticeactivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
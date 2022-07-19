package com.example.dtuadminkapil.User.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.dtuadminkapil.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EbookActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private List<ebookdata> list;
    private EbookAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
        recyclerView = findViewById(R.id.ebookrecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");
        getData();



    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snapshot100 : snapshot.getChildren()){
                    ebookdata data = snapshot100.getValue(ebookdata.class);
                    list.add(data);
                }
                adapter = new EbookAdapter(EbookActivity.this,list);
                recyclerView.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EbookActivity.this, "database", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
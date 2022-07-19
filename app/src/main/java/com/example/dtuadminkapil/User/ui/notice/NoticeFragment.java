package com.example.dtuadminkapil.User.ui.notice;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dtuadminkapil.R;
import com.example.dtuadminkapil.notice.Noticedata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {


    private RecyclerView noticerecyler;
    private ProgressBar progressBar;
    private ArrayList<Noticedata> list;
    private userNoticeAdapter adapter;

    private DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_notice, container, false);

        noticerecyler = view.findViewById(R.id.noticerecycler);
        progressBar = view.findViewById(R.id.progressbar2);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");

        noticerecyler.setLayoutManager(new LinearLayoutManager(getContext()));
        noticerecyler.setHasFixedSize(true);
        getNotice();
        return view;
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

                adapter = new userNoticeAdapter(getContext(),list);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                noticerecyler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    }

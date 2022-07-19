package com.example.dtuadminkapil.User.ui.faculty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dtuadminkapil.R;
import com.example.dtuadminkapil.faculty.UpdateTeacherActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class userTeacherAdapter extends RecyclerView.Adapter<userTeacherAdapter.TeacherViewAdapter> {

    private final List<userteacherdata> list;
    private final Context context;

    public userTeacherAdapter(List<userteacherdata> list, Context context, String category) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public TeacherViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userfaculty_item_layout, parent, false);
        return new TeacherViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewAdapter holder, int position) {

        userteacherdata item = list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.post.setText(item.getPost());

        try {
            Glide.with(context).load(item.getImage()).into(holder.imageview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TeacherViewAdapter extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView email;
        private final TextView post;
        private final ImageView imageview;


        public TeacherViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.teachername);
            email = itemView.findViewById(R.id.teacheremail);
            post = itemView.findViewById(R.id.teacherpost);
            imageview = itemView.findViewById(R.id.teacherimage);
        }
    }
}

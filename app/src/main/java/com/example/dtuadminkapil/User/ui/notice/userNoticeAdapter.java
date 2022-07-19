package com.example.dtuadminkapil.User.ui.notice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtuadminkapil.R;
import com.example.dtuadminkapil.notice.Noticedata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class userNoticeAdapter extends RecyclerView.Adapter<userNoticeAdapter.NoticeViewAdapter>
{
    private Context context;
    private ArrayList<Noticedata> list;
    private NoticeViewAdapter holder;
    private int position;

    public userNoticeAdapter(Context context, ArrayList<Noticedata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.usernewsfeed_item_layout,parent,false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder,int position) {


        Noticedata currentItem = list.get(position);
        holder.userNoticetitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());

        try {
            if(currentItem.getImage()!=null)
            Picasso.get().load(currentItem.getImage()).into(holder.usernoticeimage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        private TextView userNoticetitle,date,time;
        private ImageView usernoticeimage;


        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            userNoticetitle = itemView.findViewById(R.id.usernoticetitle);
            usernoticeimage  = itemView.findViewById(R.id.usernoticeimage);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }

}

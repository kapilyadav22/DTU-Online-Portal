package com.example.dtuadminkapil.notice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtuadminkapil.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter>
{
    private Context context;
    private ArrayList<Noticedata> list;
    private NoticeViewAdapter holder;
    private int position;

    public NoticeAdapter(Context context, ArrayList<Noticedata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout,parent,false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder,int position) {


        Noticedata currentItem = list.get(position);
        holder.deleteNoticetitle.setText(currentItem.getTitle());


        try {
            if(currentItem.getImage()!=null)
            Picasso.get().load(currentItem.getImage()).into(holder.deletenoticeimage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.deletNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete this notice?");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Notice");
                                reference.child(currentItem.getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                notifyItemRemoved(holder.getAdapterPosition());
                            }
                        }
                );

                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog=null;
                try {
                 dialog = builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(dialog!=null)
                    dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        private Button deletNotice;
        private TextView deleteNoticetitle;
        private ImageView deletenoticeimage;


        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            deletNotice = itemView.findViewById(R.id.Deletenotice);
            deleteNoticetitle = itemView.findViewById(R.id.Deletenoticetitle);
            deletenoticeimage  = itemView.findViewById(R.id.deletenoticeimage);
        }
    }

}

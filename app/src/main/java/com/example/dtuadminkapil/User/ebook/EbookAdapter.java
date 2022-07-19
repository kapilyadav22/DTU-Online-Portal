package com.example.dtuadminkapil.User.ebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtuadminkapil.R;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewholder> {

    private Context context;
    private List<ebookdata>  list;

    public EbookAdapter(Context context, List<ebookdata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebookitemlayout,parent,false);
        return new EbookViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewholder holder, int position) {
            holder.ebookname.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "We opened the pdf", Toast.LENGTH_SHORT).show();
                }
            });

            holder.ebookdownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "download", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewholder extends RecyclerView.ViewHolder {
       private TextView ebookname;
       private ImageView ebookdownload;

        public EbookViewholder(@NonNull View itemView) {
            super(itemView);
            ebookdownload = itemView.findViewById(R.id.ebookdownload);
            ebookname = itemView.findViewById(R.id.ebookname);
        }
    }
}

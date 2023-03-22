package com.example.wallypexels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class wallpaperAdapter extends RecyclerView.Adapter<wallpaperAdapter.ViewHolder> {
    ArrayList<String> wallpaperRV;
    Context context;

    public wallpaperAdapter(ArrayList<String> wallpaperRV, Context context) {
        this.wallpaperRV = wallpaperRV;
        this.context = context;
    }

    @NonNull
    @Override
    public wallpaperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaperitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wallpaperAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       // Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        //holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),android.R.anim.slide_in_left));
        Glide.with(context).load(wallpaperRV.get(position)).into(holder.wallimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,homewallpaperdownload.class);
                intent.putExtra("imgurl",wallpaperRV.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperRV.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView wallimg;
        //CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wallimg = itemView.findViewById(R.id.wallpaperimg);
            //cardView = itemView.findViewById(R.id.cardview);
        }
    }
}

package com.example.wallypexels;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {
    Context context;
    ArrayList<String> CategoryName;

    public categoryAdapter(Context context, ArrayList<String> categoryName) {
        this.context = context;
        CategoryName = categoryName;
    }

    @NonNull
    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cartegoryitemview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.ViewHolder holder, int position) {
        String CName = CategoryName.get(position);
        holder.textView.setText(CName.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Categroyviewpage.class);
                intent.putExtra("CategoryName",CName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CategoryName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.categoryImg);
            textView = itemView.findViewById(R.id.categoryName);
        }
    }
}

package com.example.musicstore.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicstore.R;

import java.util.ArrayList;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewDataHolder> {

    ArrayList<ProductModel> list;

    public HomeProductAdapter(ArrayList<ProductModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeProductAdapter.ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductAdapter.ViewDataHolder holder, int position) {
        ProductModel product = list.get(position);
        holder.ivImage.setImageBitmap(product.getImage());
        holder.tvName.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());
        holder.tvPrice.setText("Precio: $" + product.getPrice());
        holder.tvCategory.setText("");
        holder.tvStock.setText("");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName;
        TextView tvDescription;
        TextView tvPrice;
        TextView tvStock;
        TextView tvCategory;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvStock = itemView.findViewById(R.id.tvStock);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}

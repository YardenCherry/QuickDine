package com.example.customerapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.MenuItem;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<MenuItem> orderList;
    private OnOrderItemRemoveListener removeListener;

    public interface OnOrderItemRemoveListener {
        void onRemove(MenuItem item, int position);
    }

    public OrderAdapter(List<MenuItem> orderList, OnOrderItemRemoveListener removeListener) {
        this.orderList = orderList;
        this.removeListener = removeListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        MenuItem item = orderList.get(position);
        holder.textName.setText(item.getName());
        holder.textDescription.setText(item.getDescription());
        holder.textPrice.setText("₪" + item.getPrice());
        holder.buttonAdd.setText("❌ הסר");

        holder.buttonAdd.setOnClickListener(v -> {
            if (removeListener != null) {
                removeListener.onRemove(item, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textDescription, textPrice;
        Button buttonAdd;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textDescription = itemView.findViewById(R.id.textDescription);
            textPrice = itemView.findViewById(R.id.textPrice);
            buttonAdd = itemView.findViewById(R.id.buttonAddToOrder);
        }
    }
}


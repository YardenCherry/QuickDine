package com.example.waiterapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.common.MenuItem;
import com.example.waiterapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private List<List<MenuItem>> orders;

    public OrdersAdapter(List<List<MenuItem>> orders) {
        this.orders = orders != null ? orders : new ArrayList<>();
    }

    public void updateData(List<List<MenuItem>> newOrders) {
        this.orders = newOrders != null ? newOrders : new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        List<MenuItem> order = orders.get(position);
        StringBuilder orderText = new StringBuilder("הזמנה #" + (position + 1) + ":\n");
        for (MenuItem item : order) {
            orderText.append("- ").append(item.getName()).append(" (₪").append(item.getPrice()).append(")\n");
        }
        holder.textOrder.setText(orderText.toString());
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textOrder;

        public OrderViewHolder(View itemView) {
            super(itemView);
            textOrder = itemView.findViewById(R.id.textOrderDetails);
        }
    }
}

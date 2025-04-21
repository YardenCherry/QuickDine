package com.example.waiterapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.BaseActivity;
import com.example.common.MenuItem;
import com.example.common.OrderStorage;

import java.util.List;

public class OrdersActivity extends BaseActivity {

    private RecyclerView recyclerOrders;
    private OrdersAdapter ordersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recyclerOrders = findViewById(R.id.recyclerOrders);
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));

        // טען את ההזמנות מ־SharedPreferences
        OrderStorage.loadOrders(this);

        // אתחול אדפטר
        ordersAdapter = new OrdersAdapter(OrderStorage.getOrders());
        recyclerOrders.setAdapter(ordersAdapter);

        // כפתור ניקוי
        Button buttonClear = findViewById(R.id.buttonClearOrders);
        buttonClear.setOnClickListener(v -> {
            OrderStorage.clear();
            OrderStorage.saveOrders(this);
            ordersAdapter.updateData(OrderStorage.getOrders());
            Toast.makeText(this, "כל ההזמנות נמחקו", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected String getActivityTitle() {
        return "הזמנות שהתקבלו";
    }
}

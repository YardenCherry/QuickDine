package com.example.customerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.BaseActivity;
import com.example.common.MenuItem;
import com.example.common.OrderStorage;

import java.util.ArrayList;

public class OrderActivity extends BaseActivity {

    private ArrayList<MenuItem> orderList;
    private TextView textTotal;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        textTotal = findViewById(R.id.textTotal);
        RecyclerView recyclerView = findViewById(R.id.recyclerOrder);
        Button buttonSend = findViewById(R.id.buttonSendOrder);

        orderList = (ArrayList<MenuItem>) getIntent().getSerializableExtra("orderList");
        if (orderList == null) orderList = new ArrayList<>();

        adapter = new OrderAdapter(orderList, (item, position) -> {
            orderList.remove(position);
            adapter.notifyItemRemoved(position);
            updateTotal();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        buttonSend.setOnClickListener(v -> {
            if (!orderList.isEmpty()) {
                OrderStorage.addOrder(orderList);
                OrderStorage.saveOrders(this); // לשמור מיד אחרי הוספה
                Toast.makeText(this, "ההזמנה נשלחה למלצר 🎉", Toast.LENGTH_LONG).show();
                orderList.clear();
                adapter.notifyDataSetChanged();
                updateTotal();
            }
        });

        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (MenuItem item : orderList) {
            total += item.getPrice();
        }
        textTotal.setText("סה״כ: ₪" + total);
    }

    @Override
    protected String getActivityTitle() {
        return "ההזמנה שלך";
    }
}

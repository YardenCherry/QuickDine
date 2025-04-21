package com.example.waiterapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.OrderStorage;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // הכנס הזמנות דמו אם אין עדיין
        OrderStorage.loadOrders(this);
        OrderStorage.insertDemoOrders(this); // ⬅️ השורה החשובה

        Button btnViewOrders = findViewById(R.id.btnViewOrders);
        btnViewOrders.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
            startActivity(intent);
        });
    }

    protected String getActivityTitle() {
        return "Waiter Application";
    }
}
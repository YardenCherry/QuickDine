package com.example.customerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.BaseActivity;
import com.example.common.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity {

    private List<MenuItem> orderList = new ArrayList<>(); // פה נשמור את ההזמנה

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView recyclerView = findViewById(R.id.recyclerMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button buttonGoToOrder = findViewById(R.id.buttonGoToOrder);
        buttonGoToOrder.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, OrderActivity.class);
            intent.putExtra("orderList", new ArrayList<>(orderList));
            startActivity(intent);
        });
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("פיצה מרגריטה", "גבינה, עגבניות, בזיליקום", 45));
        menuItems.add(new MenuItem("פסטה בולונז", "רוטב עגבניות עם בשר טחון", 55));
        menuItems.add(new MenuItem("סלט יווני", "עגבניות, מלפפונים, גבינה בולגרית", 35));

        MenuAdapter adapter = new MenuAdapter(menuItems, new MenuAdapter.OnMenuItemClickListener() {
            @Override
            public void onAddToOrder(MenuItem item) {
                orderList.add(item);
                Toast.makeText(MenuActivity.this,
                        item.getName() + " נוסף להזמנה", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected String getActivityTitle() {
        return "תפריט המסעדה";
    }
}

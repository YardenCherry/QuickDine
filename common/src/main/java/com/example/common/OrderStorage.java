package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderStorage {

    private static final String PREF_NAME = "OrderPrefs";
    private static final String KEY_ORDERS = "orders";

    private static final List<List<MenuItem>> orders = new ArrayList<>();

    public static void addOrder(List<MenuItem> order) {
        orders.add(new ArrayList<>(order));
    }

    public static List<List<MenuItem>> getOrders() {
        return orders;
    }

    public static void clear() {
        orders.clear();
    }

    public static void saveOrders(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("orders", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        JSONArray ordersArray = new JSONArray();
        for (List<MenuItem> order : orders) {
            JSONArray orderArray = new JSONArray();
            for (MenuItem item : order) {
                try {
                    JSONObject itemJson = new JSONObject();
                    itemJson.put("name", item.getName());
                    itemJson.put("description", item.getDescription());
                    itemJson.put("price", item.getPrice());
                    orderArray.put(itemJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ordersArray.put(orderArray);
        }

        editor.putString("orders_json", ordersArray.toString());
        editor.apply();
    }

    public static void loadOrders(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("orders", Context.MODE_PRIVATE);
        String jsonString = prefs.getString("orders_json", null);
        if (jsonString == null) return;

        orders.clear();
        try {
            JSONArray ordersArray = new JSONArray(jsonString);
            for (int i = 0; i < ordersArray.length(); i++) {
                JSONArray orderArray = ordersArray.getJSONArray(i);
                List<MenuItem> order = new ArrayList<>();
                for (int j = 0; j < orderArray.length(); j++) {
                    JSONObject itemJson = orderArray.getJSONObject(j);
                    MenuItem item = new MenuItem(
                            itemJson.getString("name"),
                            itemJson.getString("description"),
                            itemJson.getInt("price")
                    );
                    order.add(item);
                }
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void insertDemoOrders(Context context) {
        if (!orders.isEmpty()) return; // לא להכניס אם כבר יש

        List<MenuItem> order1 = new ArrayList<>();
        order1.add(new MenuItem("פיצה מרגריטה", "גבינה, עגבניות, בזיליקום", 45));
        order1.add(new MenuItem("סלט יווני", "עגבניות, מלפפונים, גבינה בולגרית", 35));

        List<MenuItem> order2 = new ArrayList<>();
        order2.add(new MenuItem("פסטה בולונז", "רוטב עגבניות עם בשר טחון", 55));
        order2.add(new MenuItem("פיצה פפרוני", "גבינה, עגבניות, פפרוני", 50));

        orders.add(order1);
        orders.add(order2);

        saveOrders(context);
    }

}



package com.example.androidproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class itemsmanage extends AppCompatActivity {

    private List<GroceryItem> groceryList;
    private RecyclerView groceryRecyclerView;
    private GroceryAdapter adapter;
private ImageView imageView5;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemsmanage);
imageView5=findViewById(R.id.imageView5);
        // Initialize grocery list
        groceryList = new ArrayList<>();

        // Load saved data
        loadGroceryData();

        // Get the text from intent and parse it into a list
        String groceryText = getIntent().getStringExtra("copiedText");
        if (groceryText != null && !groceryText.isEmpty()) {
            String[] items = groceryText.split("\n"); // Assuming items are newline-separated
            for (String item : items) {
                // Add the item only if it doesn't already exist
                if (!isItemAlreadyPresent(item)) {
                    groceryList.add(new GroceryItem(item, false)); // Initially not purchased
                }
            }
            saveGroceryData(); // Save the new items
        }

        // Setup RecyclerView
        groceryRecyclerView = findViewById(R.id.groceryRecyclerView);
        groceryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GroceryAdapter(this, groceryList);
        groceryRecyclerView.setAdapter(adapter);

        // Handle item toggle save
        adapter.setOnItemCheckedChangeListener((position, isChecked) -> {
            GroceryItem item = groceryList.get(position);
            item.setPurchased(isChecked);
            saveGroceryData();  // Save the updated purchased state
        });
        Button sendSmsButton = findViewById(R.id.sendSmsButton);
        sendSmsButton.setOnClickListener(v -> sendGroceryListBySms());
        imageView5.setOnClickListener(v -> gotoprevi());
    }

    private void gotoprevi(){

        Intent intent = new Intent(this, view_groc.class);
        startActivity(intent);
    }

    private boolean isItemAlreadyPresent(String itemName) {
        for (GroceryItem item : groceryList) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    private void saveGroceryData() {
        SharedPreferences preferences = getSharedPreferences("groceryData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> savedData = new HashSet<>();
        for (GroceryItem item : groceryList) {
            // Save each item as "name|purchased" format
            savedData.add(item.getName() + "|" + item.isPurchased());
        }
        editor.putStringSet("groceryItems", savedData);
        editor.apply();
    }

    private void loadGroceryData() {
        SharedPreferences preferences = getSharedPreferences("groceryData", MODE_PRIVATE);
        Set<String> savedData = preferences.getStringSet("groceryItems", null);

        if (savedData != null) {
            for (String itemData : savedData) {
                String[] parts = itemData.split("\\|");
                if (parts.length == 2) {
                    String name = parts[0];
                    boolean purchased = Boolean.parseBoolean(parts[1]);
                    groceryList.add(new GroceryItem(name, purchased));
                }
            }
        }
    }


    private String generateGroceryListMessage() {
        StringBuilder message = new StringBuilder("My Grocery List:\n");
        for (GroceryItem item : groceryList) {
            message.append(item.getName());
            if (item.isPurchased()) {
                message.append(" (Purchased)");
            }
            message.append("\n");
        }
        return message.toString();
    }

    // Send the grocery list via SMS
    // Send the grocery list via any compatible app
    private void sendGroceryListBySms() {
        String message = generateGroceryListMessage(); // Generate the grocery list as a message
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain"); // Specify the type of content
        intent.putExtra(Intent.EXTRA_TEXT, message); // Add the grocery list as the message body

        // Check if there's an app that can handle this intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Share Grocery List")); // Allow user to pick an app
        } else {
            // Handle the case where no compatible app is available
            Toast.makeText(this, "No app found to share the list", Toast.LENGTH_SHORT).show();
        }
    }

}
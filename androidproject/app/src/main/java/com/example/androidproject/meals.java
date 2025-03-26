package com.example.androidproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

public class meals extends AppCompatActivity {
    private static final String TAG = "MealsActivity";
    private ImageView imageView6;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals);

        // Retrieve the copied text from the Intent
        Intent intent = getIntent();
        String copiedText = intent.getStringExtra("copiedText");
imageView6=findViewById(R.id.imageView6);
        // Find all the TextViews in the destination layout
        TextView textView1 = findViewById(R.id.textView14);
        TextView textView2 = findViewById(R.id.textView16);
        TextView textView3 = findViewById(R.id.textView17);
        TextView textView4 = findViewById(R.id.textView18);
        TextView textView5 = findViewById(R.id.textView19);
        TextView textView6 = findViewById(R.id.textView20);
        TextView textView7 = findViewById(R.id.textView21);

        // Add TextViews to an array for easier management
        TextView[] textViews = {textView1, textView2, textView3,textView4,textView5,textView6,textView7};
        String[] keys = {"day1", "day2", "day3", "day4", "day5", "day6", "day7"};
        // Show a dialog to let the user select the TextView
        showSelectionDialog(copiedText, textViews);

        for (int i = 0; i < textViews.length; i++) {
            String savedText = loadTextViewData(keys[i]);
            Log.d(TAG, "Loaded " + keys[i] + ": " + savedText);
            textViews[i].setText(savedText);
        }


        ImageView trashIconDay1 = findViewById(R.id.trashIconDay1);
        ImageView trashIconDay2 = findViewById(R.id.trashIconDay2);
        ImageView trashIconDay3 = findViewById(R.id.trashIconDay3);
        ImageView trashIconDay4 = findViewById(R.id.trashIconDay4);
        ImageView trashIconDay5 = findViewById(R.id.trashIconDay5);
        ImageView trashIconDay6 = findViewById(R.id.trashIconDay6);
        ImageView trashIconDay7 = findViewById(R.id.trashIconDay7);
        TextView day1TextView1 = findViewById(R.id.textView14);
        TextView day1TextView2 = findViewById(R.id.textView16);
        TextView day1TextView3 = findViewById(R.id.textView17);
        TextView day1TextView4 = findViewById(R.id.textView18);
        TextView day1TextView5 = findViewById(R.id.textView19);
        TextView day1TextView6 = findViewById(R.id.textView20);
        TextView day1TextView7 = findViewById(R.id.textView21);

        trashIconDay1.setOnClickListener(v -> {
            // Clear the text in textView14
            day1TextView1.setText("");
            saveTextViewData(keys[0], "");
            // Show a confirmation Toast
            Toast.makeText(this, "Day 1 deleted!", Toast.LENGTH_SHORT).show();
        });

        trashIconDay2.setOnClickListener(v -> {
            // Clear the text in textView14
            day1TextView2.setText("");
            saveTextViewData(keys[1], "");
            // Show a confirmation Toast
            Toast.makeText(this, "Day 1 deleted!", Toast.LENGTH_SHORT).show();
        });


        trashIconDay3.setOnClickListener(v -> {
            // Clear the text in textView14
            day1TextView3.setText("");
            saveTextViewData(keys[2], "");
            // Show a confirmation Toast
            Toast.makeText(this, "Day 1 deleted!", Toast.LENGTH_SHORT).show();
        });



        trashIconDay4.setOnClickListener(v -> {
            // Clear the text in textView14
            day1TextView4.setText("");
            saveTextViewData(keys[3], "");
            // Show a confirmation Toast
            Toast.makeText(this, "Day 1 deleted!", Toast.LENGTH_SHORT).show();
        });


        trashIconDay5.setOnClickListener(v -> {
            // Clear the text in textView14
            day1TextView5.setText("");
            saveTextViewData(keys[4], "");
            // Show a confirmation Toast
            Toast.makeText(this, "Day 1 deleted!", Toast.LENGTH_SHORT).show();
        });



        trashIconDay6.setOnClickListener(v -> {
            // Clear the text in textView14
            day1TextView6.setText("");
            saveTextViewData(keys[5], "");
            // Show a confirmation Toast
            Toast.makeText(this, "Day 1 deleted!", Toast.LENGTH_SHORT).show();
        });



        trashIconDay7.setOnClickListener(v -> {
            // Clear the text in textView14
            day1TextView7.setText("");
            saveTextViewData(keys[6], "");
            // Show a confirmation Toast
            Toast.makeText(this, "Day 1 deleted!", Toast.LENGTH_SHORT).show();
        });










        imageView6.setOnClickListener(v -> gotoprevi());
    }

    private void gotoprevi(){

        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    private void showSelectionDialog(String copiedText, TextView[] textViews) {
        // Prepare a list of labels for the dialog
        String[] labels = {"Day 1", "Day 2", "Day 3","Day 4","Day 5","Day 6","Day 7"};
        String[] keys = {"day1", "day2", "day3", "day4", "day5", "day6", "day7"};
        // Create and show an AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("Choose where to paste")
                .setItems(labels, (dialog, which) -> {
                    // Paste the text into the selected TextView
                    textViews[which].setText(copiedText);


                    saveTextViewData(keys[which], copiedText);
                })
                .show();
    }

    private void saveTextViewData(String key, String value) {
        getSharedPreferences("mealsData", MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply();
        Log.d(TAG, "Saved " + key + ": " + value);
    }

    private String loadTextViewData(String key) {
        return getSharedPreferences("mealsData", MODE_PRIVATE)
                .getString(key, ""); // Default value is an empty string
    }

    }

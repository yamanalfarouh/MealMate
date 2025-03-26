package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class SecondActivity extends AppCompatActivity {
    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second); // Make sure activity_second.xml exists

        TextView sourceTextView = findViewById(R.id.vegname);
        Button copyButton = findViewById(R.id.vegbutt);
        TextView grocview = findViewById(R.id.textView5);
imageView2=findViewById(R.id.imageView2);
        copyButton.setOnClickListener(v -> {
            // Get the text from the TextView
            String textToCopy = sourceTextView.getText().toString();
            String textToCopyy = grocview.getText().toString();

            // Pass the text to the Meals activity
            Intent intent = new Intent(SecondActivity.this, meals.class);
            intent.putExtra("copiedText", textToCopy);
            startActivity(intent);

            // Pass the text to the ItemsManage activity
            Intent intentt = new Intent(SecondActivity.this, itemsmanage.class);
            intentt.putExtra("copiedText", textToCopyy);
            startActivity(intentt);
        });

        imageView2.setOnClickListener(v -> gotoprevi());
    }

    private void gotoprevi(){

        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

}

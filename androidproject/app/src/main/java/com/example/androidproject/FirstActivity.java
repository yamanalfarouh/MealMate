package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    ImageView imageView1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first); // Ensure this is correct

        TextView sourceTextView = findViewById(R.id.chickenstir);
        Button copyButton = findViewById(R.id.chickenbutt);
        TextView grocview = findViewById(R.id.textView5);
        imageView1=findViewById(R.id.imageView1);
        copyButton.setOnClickListener(v -> {
            // Get the text from the TextView
            String textToCopy = sourceTextView.getText().toString();
            String textToCopyy = grocview.getText().toString();

            // Pass the text to the Meals activity
            Intent intent = new Intent(FirstActivity.this, meals.class);
            intent.putExtra("copiedText", textToCopy);
            startActivity(intent);

            // Pass the text to the ItemsManage activity
            Intent intentt = new Intent(FirstActivity.this, itemsmanage.class);
            intentt.putExtra("copiedText", textToCopyy);
            startActivity(intentt);
        });

        imageView1.setOnClickListener(v -> gotoprevi());
    }

    private void gotoprevi(){

        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }


}

package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ThirdActivity extends AppCompatActivity {
ImageView imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TextView sourceTextView = findViewById(R.id.textviewnamespah);
        Button copyButton = findViewById(R.id.addmealspah);
        TextView grocview = findViewById(R.id.textView5);
imageView3=findViewById(R.id.imageView3);
        copyButton.setOnClickListener(v -> {
            // Get the text from the TextView
            String textToCopy = sourceTextView.getText().toString();
            String textToCopyy = grocview.getText().toString();

            // Pass the text to the Meals activity
            Intent intent = new Intent(ThirdActivity.this, meals.class);
            intent.putExtra("copiedText", textToCopy);
            startActivity(intent);

            // Pass the text to the ItemsManage activity
            Intent intentt = new Intent(ThirdActivity.this, itemsmanage.class);
            intentt.putExtra("copiedText", textToCopyy);
            startActivity(intentt);
        });



        imageView3.setOnClickListener(v -> gotoprevi());
    }

    private void gotoprevi(){

        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }


}

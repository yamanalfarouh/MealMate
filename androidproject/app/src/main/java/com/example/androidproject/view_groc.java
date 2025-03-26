package com.example.androidproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class view_groc extends AppCompatActivity {

ImageView imageView9;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.view_groc);
imageView9=findViewById(R.id.imageView9);

        imageView9.setOnClickListener(v -> gotoprevi());
    }

    private void gotoprevi(){

        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    public void gotootherpaggeefavgroc(View view) {
        Intent intent = new Intent(this, favgroc.class);
        startActivity(intent);
    }

    public void gotootherpaggeeitemmanag(View view) {
        Intent intent = new Intent(this, itemsmanage.class);
        startActivity(intent);
    }


}
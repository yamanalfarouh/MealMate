package com.example.androidproject;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

    Button registerinitial;
    Button materialButton;
    private EditText nameEditText, emailEditText, passwordEditText;



    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        registerinitial = (Button) findViewById(R.id.chickenbutt);
        materialButton = (Button) findViewById(R.id.materialButton);


        nameEditText = findViewById(R.id.editTextTextEmailAddress2); // Add proper ID in XML for name
        emailEditText = findViewById(R.id.emailtextt); // Add proper ID in XML for email
        passwordEditText = findViewById(R.id.passwordd); // Add proper ID in XML for password




        emailEditText = findViewById(R.id.emaillogin); // Replace with the actual ID
        passwordEditText = findViewById(R.id.passwordlogin); // Replace with the actual ID
        dbHelper = new DatabaseHelper(this);







    }




    public void gotootherpage(View view) {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
    public void gotootherpagee(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void gotootherpagge(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void gotootherpaggeecreate(View view) {
        Intent intent = new Intent(this, meals.class);
        startActivity(intent);
    }

    public void gotootherpaggeerecipe(View view) {
        Intent intent = new Intent(this, create_recipe.class);
        startActivity(intent);
    }

    public void gotootherpaggeegrocery(View view) {
        Intent intent = new Intent(this, view_groc.class);
        startActivity(intent);
    }

    public void gotootherpaggeefavgroc(View view) {
        Intent intent = new Intent(this, favgroc.class);
        startActivity(intent);
    }
    public void gotootherpaggeeitemmanag(View view) {
        Intent intent = new Intent(this, favgroc.class);
        startActivity(intent);
    }


    public void registerUser(View view) {
        // Get the text input by the user
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Check if any field is empty
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // Display an error message if any field is empty
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            // Check if the email already exists
            if (dbHelper.checkEmailExists(email)) {
                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
            } else {
                // Insert user into the database
                long userId = dbHelper.insertUser(name, email, password);
                if (userId > 0) {
                    // User successfully signed up
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, home.class);
                    startActivity(intent);
                } else {
                    // Error inserting user into the database
                    Toast.makeText(this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }






    public void loginUser(View view) {
        // Get user input
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate fields
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check login credentials
        boolean isAuthenticated = dbHelper.authenticateUser(email, password);

        if (isAuthenticated) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }







}



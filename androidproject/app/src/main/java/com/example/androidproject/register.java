package com.example.androidproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
private ImageView imageView8;
    // Declare the EditText and ImageView variables
    private EditText nameEditText, emailEditText, passwordEditText;
    private ImageView passwordToggle;

    // Variable to track password visibility state
    private boolean isPasswordVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
imageView8=findViewById(R.id.imageView8);
        // Initialize EditTexts and password toggle
        nameEditText = findViewById(R.id.editTextTextEmailAddress2);
        emailEditText = findViewById(R.id.emailtextt);
        passwordEditText = findViewById(R.id.passwordd);
        passwordToggle = findViewById(R.id.passwordToggle); // Add proper ID for the eye icon in XML

        // Set up a click listener for the password toggle icon
        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
        imageView8.setOnClickListener(v -> gotoprevi());
    }

    private void gotoprevi(){

        Intent intent = new Intent(this, MainActivity.class);
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
        } else if (!isValidEmail(email)) {
            // Validate email format
            Toast.makeText(this, "Please enter a valid email in the format: yaman@anything.com", Toast.LENGTH_SHORT).show();
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

    private boolean isValidEmail(String email) {
        // Correct regex for a general email ending in .com
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$";
        return email.matches(emailPattern);
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide the password
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordToggle.setImageResource(R.drawable.password_icon); // Set appropriate icon for closed eye
            isPasswordVisible = false;
        } else {
            // Show the password
            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordToggle.setImageResource(R.drawable.password_icon); // Set appropriate icon for open eye
            isPasswordVisible = true;
        }
        // Move cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.getText().length());
    }
}

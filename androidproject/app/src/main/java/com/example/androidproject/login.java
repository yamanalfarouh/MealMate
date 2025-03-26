package com.example.androidproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
private ImageView imageView7;
    private EditText emailEditText, passwordEditText;
    private DatabaseHelper dbHelper;
    private ImageView passwordToggle;
    private boolean isPasswordVisible = false;
    private TextView forgotpass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Initialize EditTexts and DatabaseHelper
        emailEditText = findViewById(R.id.emaillogin); // Replace with the actual ID
        passwordEditText = findViewById(R.id.passwordlogin); // Replace with the actual ID
        passwordToggle = findViewById(R.id.passwordToggle); // Add proper ID for the eye icon in XML
        forgotpass=findViewById(R.id.forgotpass);
        dbHelper = new DatabaseHelper(this);
imageView7=findViewById(R.id.imageView7);
        forgotpass.setOnClickListener(v -> forgotpass());
        passwordToggle.setOnClickListener(v -> togglePasswordVisibility());
        imageView7.setOnClickListener(v -> gotoprevi());
    }

    private void gotoprevi(){

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
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
    private void forgotpass(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);

    }
}

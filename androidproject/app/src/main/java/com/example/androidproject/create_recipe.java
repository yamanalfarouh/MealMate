package com.example.androidproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class create_recipe extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText ingredientsEditText, instructionsEditText;
    private Button selectImageButton, viewRecipeButton;
    private ImageView recipeImageView;
    private TextView recipeDetailsTextView;
    private Uri imageUri;
    private Bitmap selectedImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recipe);
        // Initialize views
        ingredientsEditText = findViewById(R.id.ingredientsEditText);
        instructionsEditText = findViewById(R.id.instructionsEditText);
        selectImageButton = findViewById(R.id.selectImageButton);
        recipeImageView = findViewById(R.id.recipeImageView);
        viewRecipeButton = findViewById(R.id.viewRecipeButton);
        recipeDetailsTextView = findViewById(R.id.recipeDetailsTextView);
      TextView recipetex2=findViewById(R.id.recipeDetailsTextView2);
        // Set onClickListener for image selection
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        // Set onClickListener for viewing the recipe
        viewRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRecipeDetails();
            }
        });
        TextView sourceTextView = findViewById(R.id.instructionsEditText3);
        Button copyButton = findViewById(R.id.addcreatbutt);

        copyButton.setOnClickListener(v -> {
            // Get the text from the TextView
            String textToCopy = sourceTextView.getText().toString();
            String textToCopyy = recipetex2.getText().toString();

            // Pass the text to the Meals activity
            Intent intent = new Intent(create_recipe.this, meals.class);
            intent.putExtra("copiedText", textToCopy);
            startActivity(intent);

            // Pass the text to the ItemsManage activity
            Intent intentt = new Intent(create_recipe.this, itemsmanage.class);
            intentt.putExtra("copiedText", textToCopyy);
            startActivity(intentt);
        });
    }
    // Open file chooser to select an image
    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    // Handle the result of the image selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                recipeImageView.setImageBitmap(selectedImage);
            } catch (Exception e) {
                e.printStackTrace();
            }}}
    // Display the recipe details
    private void displayRecipeDetails() {
        String ingredients = ingredientsEditText.getText().toString();
        String instructions = instructionsEditText.getText().toString();
        //Check if all fields are filled
        if (ingredients.isEmpty() || instructions.isEmpty() || imageUri == null) {
            Toast.makeText(create_recipe.this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show();
        } else {
            TextView recipetex2=findViewById(R.id.recipeDetailsTextView2);
            String recipeDetails = "Ingredients:\n" + ingredients + "\n\nInstructions:\n" + instructions;
          String ingredDetail=ingredients;
            recipeDetailsTextView.setText(recipeDetails);
            recipetex2.setText(ingredDetail);
            // Scroll to the recipe details
            ScrollView scrollView = findViewById(android.R.id.content).getRootView().findViewById(R.id.scrollView);
            scrollView.post(() -> scrollView.smoothScrollTo(0, recipeDetailsTextView.getTop()));
        }
    }









}

package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class home extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float accel; // Acceleration apart from gravity
    private float accelCurrent; // Current acceleration including gravity
    private float accelLast; // Last acceleration including gravity
    private boolean isNavigating = false; // To prevent multiple navigations on a single shake

    int[] images = {R.drawable.chickenry99, R.drawable.rroasted, R.drawable.recipeimagespagh};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home);

        // ViewPager setup
        ViewPager viewPager = findViewById(R.id.viewPager);
        MyAdapter myAdapter = new MyAdapter(this, images);
        viewPager.setAdapter(myAdapter);

        // Initialize shake detection
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }

        accel = 0.00f;
        accelCurrent = SensorManager.GRAVITY_EARTH;
        accelLast = SensorManager.GRAVITY_EARTH;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            accelLast = accelCurrent;
            accelCurrent = (float) Math.sqrt((x * x) + (y * y) + (z * z));
            float delta = accelCurrent - accelLast;
            accel = accel * 0.9f + delta; // Perform low-cut filter

            if (accel > 12 && !isNavigating) { // Adjust sensitivity as needed
                onShakeDetected();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used, but required by interface
    }

    private void onShakeDetected() {
        isNavigating = true; // Prevent duplicate navigation
        Intent intent = new Intent(this, meals.class); // Replace `TargetActivity` with the desired activity
        startActivity(intent);

        // Reset the navigation flag after a delay
        new android.os.Handler().postDelayed(() -> isNavigating = false, 1000);
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
}

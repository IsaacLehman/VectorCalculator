package com.mobiledevolpment.isaac.vectorcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);
        Intent intent = new Intent(this, Vectors.class);
        startActivity(intent);
        finish();
    }
}

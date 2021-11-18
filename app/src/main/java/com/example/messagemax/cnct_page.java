package com.example.messagemax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class cnct_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnct_page);
    }

    boolean pressed = false;

    @Override
    public void onBackPressed() {
        if (pressed) {
            finishAffinity();
            System.exit(0);
        } else {
            Toast.makeText(getApplicationContext(), "Click back again to exit", Toast.LENGTH_SHORT).show();
            pressed = true;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                pressed = true;
            }
        };

        new Handler().postDelayed(runnable, 2000);
    }
}
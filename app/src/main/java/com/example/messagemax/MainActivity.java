package com.example.messagemax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.sign_up_screen);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpPage();
            }
        });

    }
    public void openSignUpPage(){
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }


    boolean pressed = false;

    @Override
    public void onBackPressed() {
        if(pressed){
            finishAffinity();
            System.exit(0);
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Click back again to exit",
                    Toast.LENGTH_SHORT).show();
            pressed=true;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                pressed = false;
            }
        };

        new Handler().postDelayed(runnable, 2000);

    }
}
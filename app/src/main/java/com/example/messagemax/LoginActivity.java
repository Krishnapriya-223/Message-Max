package com.example.messagemax;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.pink));

        setContentView(R.layout.activity_login);

        TextView sign_up_button_text = findViewById(R.id.sign_up_screen_text);
        sign_up_button_text.setOnClickListener(new View.OnClickListener() {
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
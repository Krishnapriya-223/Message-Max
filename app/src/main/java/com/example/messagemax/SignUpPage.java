package com.example.messagemax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(SignUpPage.this, R.color.pink));

        setContentView(R.layout.activity_sign_up_page);

        TextView log_in_screen_text = findViewById(R.id.log_in_screen_text);
        log_in_screen_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogInPage();
            }
        });

    }
    public void openLogInPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
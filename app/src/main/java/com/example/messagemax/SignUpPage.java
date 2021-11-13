package com.example.messagemax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        TextView textView = findViewById(R.id.log_in_screen);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogInPage();
            }
        });

    }
    public void openLogInPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
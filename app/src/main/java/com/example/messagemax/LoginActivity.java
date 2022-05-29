package com.example.messagemax;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText email_entered_login, password_entered_login;
    Button login_button;
    FirebaseAuth authentication_login;
    DatabaseReference userReference_login;
    ProgressDialog progress_dialog_login;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.pink));

        setContentView(R.layout.activity_login);

        email_entered_login = findViewById(R.id.email_entered_login);
        password_entered_login = findViewById(R.id.password_entered_login);
        login_button = findViewById(R.id.login_button);


        authentication_login = FirebaseAuth.getInstance();
        userReference_login = FirebaseDatabase.getInstance().getReference().child("Users");


        progress_dialog_login = new ProgressDialog(LoginActivity.this);
        progress_dialog_login.setTitle("Authentication");
        progress_dialog_login.setMessage("Please wait until successful authentication");


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email_entered_login.getText().toString().equals("")||
                        password_entered_login.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    authentication_login.signInWithEmailAndPassword(email_entered_login.getText().toString(), password_entered_login.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, " Logged in successfully", Toast.LENGTH_SHORT).show();

                                        progress_dialog_login.dismiss();

                                        Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                                        startActivity(intent);

                                        finish();

                                    }


                                    else
                                    {
                                        Toast.makeText(LoginActivity.this, ", ERROR: "+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        progress_dialog_login.dismiss();

                                    }
                                }
                            });


                }


            }
        });


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

    protected void onStart()
    {
        super.onStart();
        if(authentication_login.getCurrentUser()!=null)
        {
            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
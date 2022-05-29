package com.example.messagemax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class SignUpPage extends AppCompatActivity {


    TextView log_in_screen_text;
    EditText name_entered, email_entered_signup, password_entered_signup, retype_password_entered;
    Button signup_button;
    FirebaseAuth authentication_signup;
    DatabaseReference userReference_signup;
    ProgressDialog progress_dialog_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(SignUpPage.this, R.color.pink));

        setContentView(R.layout.activity_sign_up_page);


        name_entered = findViewById(R.id.name_entered);
        email_entered_signup = findViewById(R.id.email_entered_signup);
        password_entered_signup = findViewById(R.id.password_entered_signup);
        retype_password_entered = findViewById(R.id.retype_password_entered);
        signup_button = findViewById(R.id.signup_button);
        log_in_screen_text = findViewById(R.id.log_in_screen_text);

        authentication_signup = FirebaseAuth.getInstance();
        userReference_signup = FirebaseDatabase.getInstance().getReference().child("Users");


        log_in_screen_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogInPage();
            }
        });

        progress_dialog_signup = new ProgressDialog(SignUpPage.this);
        progress_dialog_signup.setTitle("Authentication");
        progress_dialog_signup.setMessage("Please wait until successful authentication");


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name_entered.getText().toString().equals("") ||
                        name_entered.getText().toString().equals("") ||
                        email_entered_signup.getText().toString().equals("") ||
                        password_entered_signup.getText().toString().equals("") ||
                        retype_password_entered.getText().toString().equals("")) {
                    Toast.makeText(SignUpPage.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (password_entered_signup.getText().toString().equals(retype_password_entered.getText().toString())) {

                        progress_dialog_signup.show();

                        authentication_signup.createUserWithEmailAndPassword(email_entered_signup.getText().toString(), password_entered_signup.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            userReference_signup.child(authentication_signup.getCurrentUser().getUid()).child("Name")
                                                    .setValue(name_entered.getText().toString());
                                            Toast.makeText(SignUpPage.this, name_entered.getText().toString() + " registered successfully", Toast.LENGTH_SHORT).show();

                                            progress_dialog_signup.dismiss();

                                            openLogInPage();


                                        }


                                        else
                                        {
                                            Toast.makeText(SignUpPage.this, name_entered.getText().toString() + ", ERROR: "+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                            progress_dialog_signup.dismiss();

                                        }

                                    }
                                });


                    } else {
                        Toast.makeText(SignUpPage.this, "Password and re-type password must be same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void openLogInPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
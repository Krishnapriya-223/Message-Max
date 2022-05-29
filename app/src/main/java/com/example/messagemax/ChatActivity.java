package com.example.messagemax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {


    TextView profile_name_chat;
    Toolbar toolbar_chat;
    FirebaseAuth authentication_chat;
    TabLayout tab_layout;
    ViewPager view_pager;
    TabAdapter tab_adapter;
    DatabaseReference userReference_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(ChatActivity.this, R.color.purple));
        setContentView(R.layout.activity_chat);

        authentication_chat = FirebaseAuth.getInstance();

        profile_name_chat = findViewById(R.id.profile_name_chat);
        userReference_chat = FirebaseDatabase.getInstance().getReference().child("Users");




        toolbar_chat = findViewById(R.id.toolbar_chat);
        setSupportActionBar(toolbar_chat);



        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
        tab_adapter = new TabAdapter(getSupportFragmentManager());


        view_pager.setAdapter(tab_adapter);
        tab_layout.setupWithViewPager(view_pager);

    /*
        userReference_chat.child(authentication_chat.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        profile_name_chat.setText(snapshot.child("Name").getValue().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout_in_menu)
        {
            authentication_chat.signOut();
            Intent intent = new Intent(ChatActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
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
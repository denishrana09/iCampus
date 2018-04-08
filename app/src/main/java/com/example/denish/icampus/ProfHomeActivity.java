package com.example.denish.icampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfHomeActivity extends AppCompatActivity {

    Button add_feed_btn,view_feed_btn,chat_room_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_home);
        getSupportActionBar().setTitle("Professor");
        //Toast.makeText(this, "Professor", Toast.LENGTH_SHORT).show();
        add_feed_btn = findViewById(R.id.add_feed_prof_btn);
        view_feed_btn = findViewById(R.id.view_feed_prof_btn);
        chat_room_btn = findViewById(R.id.chatroom_prof_btn);

        add_feed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfHomeActivity.this, "inside add Feed OnClick", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProfHomeActivity.this,AddFeedActivity.class));
            }
        });

        view_feed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FeedActivity.class));
            }
        });

        chat_room_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChatActivity.class));
            }
        });
    }
}

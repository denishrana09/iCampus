package com.example.denish.icampus;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.denish.icampus.Model.AnnounceDataModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeedActivity extends AppCompatActivity {

    private static final String TAG = "AddFeedActivity";
    FirebaseDatabase mFdb;
    DatabaseReference mRef;
    Button add;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed);

        mFdb = FirebaseDatabase.getInstance();
        mRef = mFdb.getReference().child("ann");

        add = findViewById(R.id.btn_feed_add);
        et = findViewById(R.id.et_feed_data);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        final String name = sharedPreferences.getString("name","");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnnounceDataModel model = new AnnounceDataModel(et.getText().toString(),name);
                Log.d(TAG, "onClick: "+ et.getText().toString() + ", " + name);
                mRef.push().setValue(model);
            }
        });

        finish();
    }
}

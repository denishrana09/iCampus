package com.example.denish.icampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.denish.icampus.Model.AnnounceDataModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {
    FirebaseDatabase mFdb;
    DatabaseReference mRef;
    ChildEventListener mCEListener;
    ArrayList<AnnounceDataModel> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        arr = new ArrayList<>();
        mFdb = FirebaseDatabase.getInstance();
        mRef = mFdb.getReference().child("ann");
        retrive();
        AnnounceAdapter announceAdapter = new AnnounceAdapter(arr, R.layout.raw_layout, getApplicationContext());
        ListView l = findViewById(R.id.annuncementList);
        l.setAdapter(announceAdapter);
    }

    void retrive() {
        if (mCEListener == null) {
            mCEListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    AnnounceDataModel user = dataSnapshot.getValue(AnnounceDataModel.class);
                    Log.d("iCampus", "onChildAdded: " + user.toString());
                    arr.add(user);
                    Log.d("iCampus", "onChildAdded: added in list " + user.getDesc());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mRef.addChildEventListener(mCEListener);
        }
    }
}

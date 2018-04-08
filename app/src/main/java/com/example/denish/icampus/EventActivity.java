package com.example.denish.icampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.denish.icampus.Model.AnnounceDataModel;
import com.example.denish.icampus.Model.EventModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    FirebaseDatabase mFdb;
    DatabaseReference mRef;
    ChildEventListener mCEListener;
    ArrayList<EventModel> arr;
    EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        arr = new ArrayList<>();
        mFdb = FirebaseDatabase.getInstance();
        mRef = mFdb.getReference().child("event");
        retrive();
        mEventAdapter = new EventAdapter(arr, getApplicationContext());
        ListView l = findViewById(R.id.eventList);
        l.setAdapter(mEventAdapter);
    }

    void retrive() {
        if (mCEListener == null) {
            mCEListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    EventModel data = dataSnapshot.getValue(EventModel.class);
                    //Log.d("iCampus", "onChildAdded: " + user.toString());
                    mEventAdapter.add(data);
                    //Log.d("iCampus", "onChildAdded: added in list " + user.getDesc());
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

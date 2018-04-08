package com.example.denish.icampus;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.denish.icampus.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by denish on 8/4/18.
 */

public class FirebaseUtil {

    private static final String TAG = "FirebaseUtil";
    
    public static   FirebaseDatabase mFirebaseDatabase=FirebaseDatabase.getInstance();
    public static DatabaseReference mUserRef = mFirebaseDatabase.getReference().child("user");
//    public static ValueEventListener mValListener;

    static FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
//    static FirebaseAuth.AuthStateListener mAuthStateListener;

    static void addUserToFB(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    //Toast.makeText(context, "pushing", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onDataChange: pushing");
                    User user = new User(mFirebaseAuth.getCurrentUser().getDisplayName(), "true", mFirebaseAuth.getCurrentUser().getEmail());
                    mUserRef.push().setValue(user);
                }else{
                    Log.d(TAG, "onDataChange: not pushing");
                    //Toast.makeText(context, "not pushing", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        //Toast.makeText(context, "before", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "addUserToFB: before");
        mUserRef.child("user").child(mFirebaseAuth.getCurrentUser().getUid())
        .addListenerForSingleValueEvent(valueEventListener);

    }
}

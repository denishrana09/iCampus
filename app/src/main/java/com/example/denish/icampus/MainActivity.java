package com.example.denish.icampus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.denish.icampus.Model.User;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserRef;
    private ChildEventListener mChildEventListener;

    private boolean accessGrant=false;
    Boolean isFirstRun;

    private String mUsername,mEmail,specialEmail;
    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;
    public ArrayList<String> list;

    Button stu,prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activeToolbar(false);

        mUsername = ANONYMOUS;
        Log.d(TAG, "onCreate: starts");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mUserRef = mFirebaseDatabase.getReference().child("user");

        prof = findViewById(R.id.prof_btn);
        stu = findViewById(R.id.stu_btn);

        isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if(isFirstRun) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("pushed", "false");
            editor.apply();
        }
        list = new ArrayList<>(5);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    mUsername = user.getDisplayName();
                    mEmail = user.getEmail();
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    pref.edit().putString("name",mUsername).apply();
                    Log.d(TAG, "onAuthStateChanged: User name : "+ mUsername);
                    onSignedInInitialized(mUsername,mEmail);
                } else {
                    // User is signed out
                    onSignedOutCleanup();
                    Log.d(TAG, "onAuthStateChanged: starting AuthUI");
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

//        if(!isFirstRun && accessGrant==false){
//            Toast.makeText(this, "AccessGrant False", Toast.LENGTH_SHORT).show();
//        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

        stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentHomeActivity.class));
            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfHomeActivity.class));
            }
        });
    }

    private void onSignedOutCleanup() {
        if(mChildEventListener != null) {
            mUserRef.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    private void onSignedInInitialized(String n,String e){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String ans = pref.getString("pushed", null);
        if(ans.equals("false")) {
            User user = new User(n, "true", e);
            mUserRef.push().setValue(user);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("pushed", "true");
            editor.apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show();
                //addUserToFB();
                //Log.d(TAG, "onActivityResult: after method");
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Signed In Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

//    public void toIntent(){
//        if(accessGrant==true){
//            startActivity(new Intent(getApplicationContext(),AddFeedActivity.class));
//        }
//    }

}

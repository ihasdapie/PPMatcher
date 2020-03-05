package com.PP.Matcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private ArrayList< ArrayList<String> > mUserOption;
    //{[average, faculty, firstName, lastName, prompt1, prompt2, prompt3...
    // ...response1, response2, response3, year]}

    private ArrayList<String> mTemp;
    private ArrayAdapter<String> arrayAdapter;
    private int i;

    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mUserOption = new ArrayList<>();


        for (ArrayList <String> x : mUserOption){
            mTemp.add(x.get(0));
        }


        arrayAdapter = new ArrayAdapter<>(this, R.layout.card, R.id.helloText, mTemp );

        SwipeFlingAdapterView flingContainer = findViewById(R.id.mainActivity_swipeFlingAdapterView);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                mTemp.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText((MainActivity.this), "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText((MainActivity.this), "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText((MainActivity.this), "click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getOtherUsers(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference mOtherUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mOtherUserDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    //{[average, faculty, firstName, lastName, prompt1, prompt2, prompt3...
                    // ...response1, response2, response3, year]}
                    ArrayList temp = new ArrayList<String>(Arrays.asList(mOtherUserDatabase.child("average").toString(),
                            mOtherUserDatabase.child("faculty").toString(), mOtherUserDatabase.child("lastName").toString(),
                            mOtherUserDatabase.child("prompt1").toString(), mOtherUserDatabase.child("prompt2").toString(),
                            mOtherUserDatabase.child("prompt2").toString(), mOtherUserDatabase.child("response1").toString(),
                            mOtherUserDatabase.child("response2").toString(), mOtherUserDatabase.child("response3").toString(),
                            mOtherUserDatabase.child("year").toString()));

                    mUserOption.add(temp);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void signOutUser(View view) {
        mFirebaseAuth.signOut();
        Toast.makeText(MainActivity.this, "Signed Out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, chooseLoginSignupActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}
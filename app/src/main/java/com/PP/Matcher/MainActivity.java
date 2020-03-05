package com.PP.Matcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private profileCard profileCards[];
    private profileArrayAdaptor ppAdaptor;

    private ArrayList< ArrayList<String> > mUserOption;
    //{[average, faculty, firstName, lastName, prompt1, prompt2, prompt3...
    // ...response1, response2, response3, year]}

    private ArrayList<String> mTemp;
    private FirebaseAuth mFirebaseAuth;
    ListView listView;
    List<profileCard> rowItems;

    private DatabaseReference mUserDb;
    private String mCurrentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mUserDb=FirebaseDatabase.getInstance().getReference().child("Users");
        mCurrentUserID = FirebaseAuth.getInstance().getUid();


        getOtherUsers();
        rowItems = new ArrayList<profileCard>();

        ppAdaptor = new profileArrayAdaptor(this, R.layout.profile, rowItems);

        SwipeFlingAdapterView flingContainer = findViewById(R.id.mainActivity_swipeFlingAdapterView);

        flingContainer.setAdapter(ppAdaptor);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                ppAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //grab UserID of profile, add to database
                Toast.makeText((MainActivity.this), "You've rejected this profile :(", Toast.LENGTH_SHORT).show();
                profileCard card = (profileCard) dataObject;
                String profileUserID = card.getUserID();
                mUserDb.child(profileUserID).child("swipedBy").child("no").child(mCurrentUserID).setValue(true);

            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText((MainActivity.this), "You've liked this profile!", Toast.LENGTH_SHORT).show();
                profileCard card = (profileCard) dataObject;
                String profileUserID = card.getUserID();
                mUserDb.child(profileUserID).child("swipedBy").child("yes").child(mCurrentUserID).setValue(true);
                isMatch(profileUserID);
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
                    profileCard newCard;
                    newCard = new profileCard(dataSnapshot.getKey(), dataSnapshot.child("firstName").getValue().toString(), dataSnapshot.child("lastName").getValue().toString(),("Average: "+
                            dataSnapshot.child("average").getValue().toString() + "%"), dataSnapshot.child("faculty").getValue().toString(), ("Year: "+ dataSnapshot.child("year").getValue().toString()),
                            dataSnapshot.child("prompt1").getValue().toString(), dataSnapshot.child("prompt2").getValue().toString(), dataSnapshot.child("prompt3").getValue().toString(),
                            dataSnapshot.child("response1").getValue().toString(), dataSnapshot.child("response2").getValue().toString(), dataSnapshot.child("response3").getValue().toString());

                    rowItems.add(newCard);
                    ppAdaptor.notifyDataSetChanged();
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

    public void editProfile(View view) {
        Intent intent = new Intent(MainActivity.this, ppCreatorActivity.class);
        intent.putExtra("mUSER_ID", mCurrentUserID);
        startActivity(intent);
        finish();
        return;
    }
    private void isMatch(String profileUserID){
        final DatabaseReference currentUserRightDb = mUserDb.child(mCurrentUserID).child("swipedBy").child(profileUserID);
        currentUserRightDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(MainActivity.this, "A Match!", Toast.LENGTH_SHORT).show();
                    mUserDb.child(dataSnapshot.getKey()).child("Matches").child(mCurrentUserID).setValue(true);
                    mUserDb.child(mCurrentUserID).child("Matches").child(dataSnapshot.getKey()).setValue(true);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}



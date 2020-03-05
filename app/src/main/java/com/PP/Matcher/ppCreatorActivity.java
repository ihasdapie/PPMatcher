package com.PP.Matcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Integer.parseInt;

public class ppCreatorActivity extends AppCompatActivity {

	private Spinner mUserFaculty;
	private EditText mUserFirstName, mUserLastName, mUserYear, mUserAverage,
			mPrompt1, mPrompt2, mPrompt3, mResponse1, mResponse2, mResponse3;
	private Button mCreateProfile;
	private CheckBox mTermsOfService;
	private SeekBar mUserInterestSimilarity;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pp_creator);

		//assign variables from xml
		final String mUserID=getIntent().getStringExtra("mUSER_ID");
		mTermsOfService=findViewById(R.id.ppCreatorActivity_checkBoxTermsOfService);
		mCreateProfile=findViewById(R.id.ppCreatorActivity_createProfileButton);
		mUserFaculty= findViewById(R.id.ppCreatorActivity_spinnerUserFaculty);
		mUserFirstName=findViewById(R.id.ppCreatorActivity_editTextFirstName);
		mUserLastName=findViewById(R.id.ppCreatorActivity_editTextLastName);
		mUserYear=findViewById(R.id.ppCreatorActivity_editTextUserYear);
		mUserAverage=findViewById(R.id.ppCreatorActivity_editTextUserAverage);
		mPrompt1=findViewById(R.id.ppCreatorActivity_editTextPrompt1);
		mPrompt2=findViewById(R.id.ppCreatorActivity_editTextPrompt2);
		mPrompt3=findViewById(R.id.ppCreatorActivity_editTextPrompt3);
		mResponse1=findViewById(R.id.ppCreatorActivity_editTextResponse1);
		mResponse2=findViewById(R.id.ppCreatorActivity_editTextResponse2);
		mResponse3=findViewById(R.id.ppCreatorActivity_editTextResponse3);
		mUserInterestSimilarity=findViewById(R.id.ppCreatorActivity_seekBarInterestSimilarity);
		final DatabaseReference mCurrentUserData = FirebaseDatabase.getInstance().getReference().child("Users").child(mUserID);

		mCreateProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTermsOfService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if (isChecked){
							mCurrentUserData.child("faculty").setValue(mUserFaculty.getSelectedItem().toString());
							mCurrentUserData.child("average").setValue(parseInt(mUserAverage.getText().toString()));
							mCurrentUserData.child("firstName").setValue(mUserFirstName.getText().toString());
							mCurrentUserData.child("lastName").setValue(mUserLastName.getText().toString());
							mUserInterestSimilarity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
								int mSeekProgress = 0;
								public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
									mSeekProgress=progress;

								}
								@Override
								public void onStartTrackingTouch(SeekBar seekBar) {

								}
								@Override
								public void onStopTrackingTouch(SeekBar seekBar) {
									mCurrentUserData.child("interestSimilarity").setValue(mSeekProgress);
								}
							});
							mCurrentUserData.child("prompt1").setValue((mPrompt1.getText().toString()));
							mCurrentUserData.child("prompt2").setValue((mPrompt2.getText().toString()));
							mCurrentUserData.child("prompt3").setValue((mPrompt3.getText().toString()));
							mCurrentUserData.child("response1").setValue(mResponse1.getText().toString());
							mCurrentUserData.child("response2").setValue(mResponse2.getText().toString());
							mCurrentUserData.child("response3").setValue(mResponse3.getText().toString());
							mCurrentUserData.child("year").setValue(parseInt(mUserYear.getText().toString()));

							Toast.makeText(ppCreatorActivity.this, "Profile Created!", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(ppCreatorActivity.this, MainActivity.class);
							startActivity(intent);
							finish();
							return;
						}
						else{
							Toast.makeText(ppCreatorActivity.this, "Please accept the Terms of Service to Continue", Toast.LENGTH_LONG).show();
						}
					}
				});
			}
		});

	}
}

package com.PP.Matcher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ppCreatorActivity extends AppCompatActivity {

	private Spinner mUserFaculty;
	private EditText mUserFirstName, mUserLastName, mUserYear, mUserAverage,
			mPrompt1, mPrompt2, mPrompt3, mResponse1, mResponse2, mResponse3;
	private Button mCreateProfile;
	private CheckBox mTermsOfService;
	private SeekBar mUserInterestSimilarity;
	private String mUserID=getIntent().getStringExtra("mUSER_ID");


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pp_creator);

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

		DatabaseReference mCurrentUserData = FirebaseDatabase.getInstance().getReference().child("")











		mCreateProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});











	}
}

package com.PP.Matcher;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

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







	}
}

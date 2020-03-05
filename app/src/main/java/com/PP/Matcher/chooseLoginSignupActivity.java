package com.PP.Matcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class chooseLoginSignupActivity extends AppCompatActivity {

	private Button mLogin, mSignup;
	//todo: Add "back" leftRightButton
	//todo: Add registration landing page + email verification


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_login_signup);

		mLogin = findViewById(R.id.activityChooseLoginSignup_loginButton);
		mSignup = findViewById(R.id.activityChooseLoginSignup_signupButton);

		mLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(chooseLoginSignupActivity.this, loginActivity.class);
				startActivity(intent);
				finish();
				return;
			}
		});

		mSignup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(chooseLoginSignupActivity.this, signupActivity.class);
				startActivity(intent);
				finish();
				return;

			}
		});


	}
}

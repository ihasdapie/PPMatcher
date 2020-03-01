package com.PP.Matcher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signupActivity extends AppCompatActivity {

	private Button mSignup, mBack;
	private EditText mEmail, mPassword, mConfirmPassword;
	private FirebaseAuth mFirebaseAuth;
	private FirebaseAuth.AuthStateListener mFireBaseAuthListener;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		mFirebaseAuth = FirebaseAuth.getInstance();

		mFireBaseAuthListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				//if auth state changes, do something
				final FirebaseUser mUsr = FirebaseAuth.getInstance().getCurrentUser();
				if (mUsr != null){
					Intent intent = new Intent(signupActivity.this, MainActivity.class);
					intent.putExtra("mUSER_ID", mFirebaseAuth.getCurrentUser());
					startActivity(intent);
					finish();
					return;

				}
			}
		};

		mSignup = findViewById(R.id.signupActivity_buttonSignup);
		mEmail =  findViewById(R.id.signupActivity_editTextEmail);
		mPassword = findViewById(R.id.signupActivity_editTextPassword);
		mConfirmPassword = findViewById(R.id.signupActivity_editTextConfirmPassword);
		mBack = findViewById(R.id.signupActivity_buttonBack);

		//TextClearing (try to handle w/ app:endIconMode)
//		mEmail.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mEmail.getText().clear();
//			}
//		});
//		mPassword.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mPassword.getText().clear();
//			}
//		});



		mConfirmPassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mConfirmPassword.getText().clear();
			}
		});

		mBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(signupActivity.this, chooseLoginSignupActivity.class);
				startActivity(intent);
				finish();
				return;
			}
		});

		mSignup.setOnClickListener(new View.OnClickListener() {
		//signup user with signup button
			@Override
			public void onClick(View v) {
				final String email = mEmail.getText().toString();
				final String pw = mPassword.getText().toString();
				final String confirm_pw = mConfirmPassword.getText().toString();

				if (confirm_pw.equals(pw))
					mFirebaseAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(signupActivity.this, new OnCompleteListener<AuthResult>() {
						@Override
						//check if successful
						public void onComplete(@NonNull Task<AuthResult> task) {
							if (task.isSuccessful() == false) {
								Toast.makeText(signupActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
							}
							if (task.isSuccessful()) {
								Toast.makeText(signupActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
								Toast.makeText(signupActivity.this, "Next Step: Create Profile", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(signupActivity.this, ppCreatorActivity.class);
								startActivity(intent);
								finish();
								return;
							}
						}
					});
				else {
					Toast.makeText(signupActivity.this, "Registration Error: Passwords do not match", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	protected void onStart(){
		super.onStart();
		mFirebaseAuth.addAuthStateListener(mFireBaseAuthListener);
	}

	@Override
	protected void onStop(){
		super.onStop();
		mFirebaseAuth.removeAuthStateListener(mFireBaseAuthListener);
	}

}

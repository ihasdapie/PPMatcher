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

public class loginActivity extends AppCompatActivity {

	private Button mLogin;
	private EditText mEmail, mPassword;

	private FirebaseAuth mFirebaseAuth;
	private FirebaseAuth.AuthStateListener mFireBaseAuthListener;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mFirebaseAuth = FirebaseAuth.getInstance();

		mFireBaseAuthListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				//if auth state changes, do something
				final FirebaseUser mUsr = FirebaseAuth.getInstance().getCurrentUser();
				if (mUsr != null){
					Intent intent = new Intent(loginActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
					return;

				}
			}
		};


		mLogin = findViewById(R.id.loginActivity_loginButton);
		mEmail = findViewById(R.id.loginActivity_editTextEmail);
		mPassword = findViewById(R.id.loginActivity_editTextPassword);

		mLogin.setOnClickListener(new View.OnClickListener() {
			//signup user with signup button
			@Override
			public void onClick(View v) {
				final String email = mEmail.getText().toString();
				final String pw = mPassword.getText().toString();
				mFirebaseAuth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
					@Override
					//check if successful
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful() == false){
							Toast.makeText(loginActivity.this,"Login Error", Toast.LENGTH_SHORT).show();
						}
					}
				});
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
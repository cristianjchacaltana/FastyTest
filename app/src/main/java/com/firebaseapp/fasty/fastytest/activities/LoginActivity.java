package com.firebaseapp.fasty.fastytest.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebaseapp.fasty.fastytest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLogInButton;
    private LinearLayout mSignUpLinearLayout;
    private FirebaseAuth mFirebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailEditText = findViewById(R.id.edittext_login_email);
        mPasswordEditText = findViewById(R.id.edittext_login_password);
        mLogInButton = findViewById(R.id.button_log_in);
        mSignUpLinearLayout = findViewById(R.id.linearlayout_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        initSignUp();
        enableLogIn();
        signInFastyTest();
    }



    private void initSignUp(){

        mSignUpLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,
                                        RegisterActivity.class);

                startActivity(intent);

            }
        });

    }


    private void enableLogIn(){

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();


                if ( !email.equals("")
                     && !password.equals("")){
                     mLogInButton.setEnabled(true);
                } else {
                    mLogInButton.setEnabled(false);
                }
            }
        };

        mEmailEditText.addTextChangedListener(textWatcher);
        mPasswordEditText.addTextChangedListener(textWatcher);

    }


    private void signInFastyTest(){

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: hola peru");


                mFirebaseAuth.signInWithEmailAndPassword(mEmailEditText.getText().toString(),
                                                        mPasswordEditText.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if ( task.isSuccessful() ){
                                    startActivity(new Intent(LoginActivity.this,
                                                            MainActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this,
                                            "No se ha podido iniciar sesion",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });

    }



}

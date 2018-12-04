package com.firebaseapp.fasty.fastytest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebaseapp.fasty.fastytest.R;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLogInButton;
    private LinearLayout mSignUpLinearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailEditText = findViewById(R.id.edittext_login_email);
        mPasswordEditText = findViewById(R.id.edittext_login_password);
        mLogInButton = findViewById(R.id.button_log_in);
        mSignUpLinearLayout = findViewById(R.id.linearlayout_sign_up);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        initSignUp();
        enableLogIn();
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



}

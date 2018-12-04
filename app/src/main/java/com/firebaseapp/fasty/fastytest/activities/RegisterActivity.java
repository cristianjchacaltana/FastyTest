package com.firebaseapp.fasty.fastytest.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.RealtimeConstans;
import com.firebaseapp.fasty.fastytest.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmPasswordEditText;
    private Button mSignUpButton;
    private RelativeLayout mRelativeLayout;
    private LinearLayout mLogInLinearLayout;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNameEditText = findViewById(R.id.edittext_register_name);
        mEmailEditText = findViewById(R.id.edittext_register_email);
        mPasswordEditText = findViewById(R.id.edittext_register_password);
        mConfirmPasswordEditText = findViewById(R.id.edittext_register_confirm_password);
        mSignUpButton = findViewById(R.id.button_sign_up);
        mRelativeLayout = findViewById(R.id.relativelayout_register_progressbar);
        mLogInLinearLayout = findViewById(R.id.linearlayout_log_in);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        initSignUp();
        initLogIn();
        enableSignUp();
    }



    private void initLogIn(){

        mLogInLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this,
                                        LoginActivity.class);
                startActivity(intent);

            }
        });

    }


    private void initSignUp(){

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String confirmPassword = mConfirmPasswordEditText.getText().toString();

                if ( validateEmail(email) ){

                    if (validatePassword(password, confirmPassword)){
                        registerNewUser(email, password);
                    } else{
                        Toast.makeText(RegisterActivity.this,
                                        getString(R.string.register_msg_four),
                                        Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(RegisterActivity.this,
                                    getString(R.string.register_msg_three),
                                    Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    private boolean validatePassword(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    private boolean validateEmail(String email){

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    private void registerNewUser(String email, String password){

        showProgressBar();

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Log.d(TAG, "registerNewUser: register successful");

                                Toast.makeText(RegisterActivity.this,
                                                getString(R.string.register_msg_two),
                                                Toast.LENGTH_SHORT).show();

                                insertUser();

                                Intent intent = new Intent(RegisterActivity.this,
                                                            MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Log.d(TAG, "resgiterNewUser: sign up failed");

                                hideProgressBar();

                                Toast.makeText(RegisterActivity.this,
                                                getString(R.string.register_error_one),
                                                Toast.LENGTH_SHORT).show();
                            }



                        }
                    });

    }

    private void showProgressBar(){
        mRelativeLayout.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mRelativeLayout.setVisibility(View.INVISIBLE);
    }


    private void enableSignUp(){

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = mNameEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String confirmPassword = mConfirmPasswordEditText.getText().toString();

                if ( !name.equals("")
                     && !email.equals("")
                     && !password.equals("")
                     && !confirmPassword.equals("")){
                    mSignUpButton.setEnabled(true);
                } else {
                    mSignUpButton.setEnabled(false);
                }
            }
        };

        mNameEditText.addTextChangedListener(textWatcher);
        mEmailEditText.addTextChangedListener(textWatcher);
        mPasswordEditText.addTextChangedListener(textWatcher);
        mConfirmPasswordEditText.addTextChangedListener(textWatcher);

    }



    private void insertUser(){

        DatabaseReference db = mFirebaseDatabase.getReference();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        User user = new User();
        user.setName(firebaseUser.getDisplayName());
        user.setPhone(firebaseUser.getPhoneNumber());
        user.setProfileImage("");
        user.setUid(firebaseUser.getUid());

        db.child(RealtimeConstans.USERS)
            .child(user.getUid())
            .setValue(user);

    }

}


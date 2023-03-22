package com.example.wallypexels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.regex.Pattern;

public class loginSignup extends AppCompatActivity {
    private Button signup,login;
    private TextView loginoption,signupoption;
    private TextInputEditText email,password,emailsignup,passsignup,ReenterPassword;
    private LinearLayout logindetail,signupdetail;
    private FirebaseAuth auth;
    private String Email;
    private String Password;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        loginoption = findViewById(R.id.loginbtn);
        signupoption = findViewById(R.id.signupbtn);
        logindetail = findViewById(R.id.logindetail);
        signupdetail = findViewById(R.id.signupdetail);
        email =findViewById(R.id.email);
        email.setTextColor(R.color.black);
        password = findViewById(R.id.password);
        emailsignup = findViewById(R.id.emailsignup);
        passsignup = findViewById(R.id.passwordsignup);
        ReenterPassword = findViewById(R.id.passwordcorrection);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();
        loginoption.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                loginoption.setBackgroundResource(R.drawable.shape_tracks);
                loginoption.setTextColor(Color.parseColor("#EcE5Ec"));
                signupoption.setBackground(null);
                signupoption.setTextColor(Color.parseColor("#FF000000"));
                signupdetail.setVisibility(View.GONE);
                logindetail.setVisibility(View.VISIBLE);
            }
        });
        signupoption.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
               signupoption.setBackgroundResource(R.drawable.shape_tracks);
               signupoption.setTextColor(Color.parseColor("#EcE5Ec"));
               loginoption.setBackground(null);
               loginoption.setTextColor(Color.parseColor("#FF000000"));
               logindetail.setVisibility(View.GONE);
               signupdetail.setVisibility(View.VISIBLE);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Email = emailsignup.getText().toString();
                    Password = passsignup.getText().toString();
                    String RePassword = ReenterPassword.getText().toString();
                    // Check if user is signed in (non-null) and update UI accordingly.

                    if(TextUtils.isEmpty(Email)){
                        emailsignup.setError("Email is required");
                        emailsignup.requestFocus();
                    }
                    else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                        Toast.makeText(loginSignup.this, "please re-enter email ", Toast.LENGTH_SHORT).show();
                        emailsignup.setError("Enter valid Email ");
                        emailsignup.requestFocus();
                    }
                    else if(TextUtils.isEmpty(Password)){
                        passsignup.setError("Password is Required ");
                        passsignup.requestFocus();
                    }
                     else if(TextUtils.isEmpty(RePassword)){
                        ReenterPassword.setError("Password Conformation is Required ");
                        ReenterPassword.requestFocus();
                    }
                   else if(!Password.equals(RePassword)){
                        Toast.makeText(loginSignup.this, "Check Password", Toast.LENGTH_SHORT).show();
                        ReenterPassword.setError("Check Password");
                        ReenterPassword.requestFocus();
                        ReenterPassword.clearComposingText();
                        passsignup.clearComposingText();
                    }
                   else {
                        // auth = FirebaseAuth.getInstance();
                        auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(loginSignup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(loginSignup.this, "Successfully completed ", Toast.LENGTH_SHORT).show();
                                    //FirebaseUser firebaseUser = auth.getCurrentUser();
                                   // ReadWriteUserDetail readWriteUserDetail = new ReadWriteUserDeatail();
                                    //firebaseUser.sendEmailVerification();
                                    Intent intent = new Intent(loginSignup.this, profiledetail.class);
                                    startActivity(intent);

                                }
                                else {
                                    try {
                                        throw task.getException();
                                    }catch (FirebaseAuthWeakPasswordException we){
                                        passsignup.setError("Weak Password");
                                        passsignup.requestFocus();
                                    }
                                    catch (FirebaseAuthInvalidCredentialsException E){
                                        emailsignup.setError("Enter valid Email ");
                                        emailsignup.requestFocus();
                                    }
                                    catch (FirebaseAuthUserCollisionException EU){
                                        emailsignup.setError("user is already register with this email. use another email");
                                        emailsignup.requestFocus();
                                    }
                                    catch (Exception e){
                                        Toast.makeText(loginSignup.this, "error occurred", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        });

                    }
                }
            });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = email.getText().toString();
                Password = password.getText().toString();
                if(TextUtils.isEmpty(Email)){
                    email.setError("Email is required");
                    email.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    Toast.makeText(loginSignup.this, "please re-enter email ", Toast.LENGTH_SHORT).show();
                    email.setError("Enter valid Email ");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(Password)){
                    password.setError("Password is Required ");
                    password.requestFocus();
                }
                else {
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(loginSignup.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(loginSignup.this, "Welcome Back ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(loginSignup.this, profiledetail.class);
                                startActivity(intent);
                            }else {
                                try {
                                    throw task.getException();
                                }catch (FirebaseAuthInvalidUserException IE){
                                    email.setError("user does not exists. so create account ");
                                    email.requestFocus();
                                }catch (FirebaseAuthInvalidCredentialsException IEE){
                                    email.setError("Invalid Credential. Kindly,Check and re-enter ");
                                    email.requestFocus();
                                }catch (Exception e){
                                    Toast.makeText(loginSignup.this, "error occurred", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(loginSignup.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser()!=null){
            Toast.makeText(this, "already logged in "+auth.getCurrentUser(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(loginSignup.this, profiledetail.class);
            startActivity(intent);
            finish();
        }
    }
}
package com.imran.android.java_firebaseemailauthenticationui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            // already signed in
        } else {
            // not signed in
        }
    }

    public void signUp(View view) {

    }

    public void signIn(View view) {

    }

    public void signOut(View view) {

    }
}
package com.imran.android.java_firebaseemailauthenticationui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    String TAG = "EmailUI_Auth";
    TextView textSigningStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSigningStatus = findViewById(R.id.textViewSigningStatus);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            // already signed in
            textSigningStatus.setText("Signed In");
        } else {
            // not signed in
            textSigningStatus.setText("Signed Out");
        }
    }

    public void signUp(View view) {

    }

    public void signIn(View view) {

    }

    public void signOut(View view) {

    }
}

package com.imran.android.java_firebaseemailauthenticationui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

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
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            // already signed in
            textSigningStatus.setText("Signed In");
            Toast.makeText(this,
                    "User already signed in, must sign out first",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Choose authentication builder
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build()
            );

            startActivityForResult(
                    AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setDefaultProvider(providers.get(0))
                    .build(),
                   1234
            );
        }
    }

    public void signOut(View view) {

    }
}

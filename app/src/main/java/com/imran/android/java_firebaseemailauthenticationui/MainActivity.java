package com.imran.android.java_firebaseemailauthenticationui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int SIGIN_REQUEST_CODE = 1234;
    String TAG = "EmailUI_Auth";
    TextView textSigningStatus;
    TextView textUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSigningStatus = findViewById(R.id.textViewSigningStatus);
        textUser = findViewById(R.id.textViewUser);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            // already signed in
            textSigningStatus.setText("Signed In");
            setCurrentUserName();
        } else {
            // not signed in
            textSigningStatus.setText("Signed Out");
            textUser.setText("No User");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGIN_REQUEST_CODE) {
            // successfully signed in
            if (resultCode == RESULT_OK) {
                textSigningStatus.setText("SIGNED IN");
                // user
                setCurrentUserName();

                Toast.makeText(this, "Successfully Signed In", Toast.LENGTH_SHORT).show();
            } else {
                // sign in failed

                Toast.makeText(this, "Unable to Sign In", Toast.LENGTH_SHORT).show();
            }
        }
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
                    SIGIN_REQUEST_CODE
            );
        }
    }

    public void signOut(View view) {

    }

    public void setCurrentUserName() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        textUser.setText(currentUser.getDisplayName());
    }
}

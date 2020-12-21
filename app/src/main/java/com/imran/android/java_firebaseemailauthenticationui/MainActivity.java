package com.imran.android.java_firebaseemailauthenticationui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
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
            updateSignedIn();
        } else {
            // not signed in
            updateSignedOut();
        }
    }

    private void updateSignedOut() {
        textSigningStatus.setText("Signed Out");
        textUser.setText("No User");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGIN_REQUEST_CODE) {
            // successfully signed in
            if (resultCode == RESULT_OK) {
                // user
                updateSignedIn();

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
            Toast.makeText(this,
                    "User already signed in, must sign out first",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Choose authentication builder
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build()
//                    new AuthUI.IdpConfig.PhoneBuilder().build(),
//                    new AuthUI.IdpConfig.GoogleBuilder().build(),
//                    new AuthUI.IdpConfig.TwitterBuilder().build(),
//                    new AuthUI.IdpConfig.GitHubBuilder().build()
            );

            startActivityForResult(
                    AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                    SIGIN_REQUEST_CODE
            );
        }
    }

    public void signOut(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is signed out
                        updateSignedOut();
                        Toast.makeText(getApplicationContext(), "User Signed Out", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteUser(View view) {
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Delete succeeded
                        } else {
                            // deletion failed
                        }
                    }
                });

        signOut(view);
    }

    public void updateSignedIn() {
        textSigningStatus.setText("SIGNED IN");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        textUser.setText(currentUser.getDisplayName());
    }

    public void dynamicLinkSignIn(View view) {
        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setAndroidPackageName(getPackageName(),
                        true, // install if not available
                        null) // minimum app version
                .setHandleCodeInApp(true)
                .setUrl("https://dynamic.auth.example.com/emailSignInLink")
                .build();
    }
}

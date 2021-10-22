package com.example.triviagroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailET, passwordET;
    TextView authStatusTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        emailET = findViewById(R.id.editTextTextPersonName);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        authStatusTV = findViewById(R.id.authTV);
        if(currentUser != null){
            authStatusTV.setText("onStart reloaded and " + currentUser.getEmail() + " is logged in");
            // Take any action needed here when screen loads and a user is logged in
        }
        else {
            authStatusTV.setText("onStart reloaded and user is null");
            // Take any action needed here when screen loads and a user is NOT logged in
        }
    }

    public void handleAuthChange(View v) {
        String email = emailET.getText().toString();
        String password = "password";//passwordET.getText().toString();
        Log.i("Denna",  email + " " + password);

        switch (v.getId()) {
            case R.id.signIn:
                signIn(email, "password");
                //
                break;
            case R.id.signUp:
                //
                signUp(email, "password");
                break;

        }
    }
    public void sendMessage(View v) {
        Intent intent = new Intent(this, choosingTheme.class);
        intent.putExtra("trivia name", "name");
        EditText FN = findViewById(R.id.editTextTextPersonName);
        String name = FN.getText().toString();
        if(name.matches("")){
            Toast.makeText(MainActivity.this, "Please Type all of the Fields", Toast.LENGTH_LONG).show();

        }else{
            startActivity(intent);
        }

    }

    public void signUp(String email, String password) {

        // If the email and password passed in are not null, then try to create a User
        if (email != null && password != null) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("Denna", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                authStatusTV.setText("Signed up " + user.getEmail() + " successfully");
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("Denna", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                authStatusTV.setText("Signed up - FAILED");
                            }
                        }
                    });
        }
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("Denna", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            authStatusTV.setText("Signed in " + user.getEmail());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("Denna", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
}
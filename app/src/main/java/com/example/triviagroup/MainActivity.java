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
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import androidx.appcompat.app.AppCompatActivity;


import java.util.HashMap;
import java.util.Map;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DB";
    private FirebaseAuth mAuth;

    EditText emailET, passwordET;
    TextView authStatusTV;
    public static String theUser;
    public static String documentId;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //////////////////////////////////////////////////////
//    private static final long START_TIME_IN_MILLIS = 600000;
//
//    private TextView mTextViewCountDown;
//    private Button mButtonStartPause;
//    private Button mButtonReset;
//    private CountDownTimer mCountDownTimer;
//    private boolean mTimerRunning;
//    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
//////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        emailET = findViewById(R.id.editTextTextPersonName);
        /////////////////////////////////////////////

        //setContentView(R.layout.activity_main);

//        mTextViewCountDown = findViewById(R.id.text_view_countdown);
//
//        mButtonStartPause = findViewById(R.id.button_start_pause);
//        mButtonReset = findViewById(R.id.button_reset);




        
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
//                                FirebaseUser user = mAuth.getCurrentUser();
                                FirebaseUser userToAdd = mAuth.getCurrentUser();
                                Map<String, Object> user = new HashMap<>();
                                user.put("points", 0);
                                user.put("gmail", email);
                                user.put("bg", "White");
                                user.put("uuid", userToAdd.getUid());
                                theUser = userToAdd.getUid();
                                authStatusTV.setText("Signed up " + userToAdd.getEmail() + " successfully");

                                db.collection("Players")
                                        .add(user)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error adding document", e);
                                            }
                                        });
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
                            FirebaseUser userToAdd = mAuth.getCurrentUser();
//                            Map<String, Object> user = new HashMap<>();
//                            user.put("points", 0);
//                            user.put("bg", "White");
//                            user.put("uuid", userToAdd.getUid());
                            System.out.println(userToAdd.getUid());
                            //theUser = userToAdd.getUid();
                            System.out.println(theUser);
                            authStatusTV.setText("Signed in " + userToAdd.getEmail());
                            // Create a reference to the cities collection
                            CollectionReference citiesRef = db.collection("Players");
                            String curUid = mAuth.getCurrentUser().getUid();
                            // Create a query against the collection.
                            Query query = citiesRef.whereEqualTo("uuid", curUid);
                            db.collection("Players")
                                    .whereEqualTo("uuid", curUid)
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                                    documentId = document.getId();
                                                }
                                            } else {
                                                Log.d(TAG, "Error getting documents: ", task.getException());
                                            }
                                        }
                                    });
                            //db.collection(theUser).addDocument("Points")
//                            db.collection("Players")
//                                    .add(user)
//                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//                                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Log.w(TAG, "Error adding document", e);
//                                        }
//                                    });

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
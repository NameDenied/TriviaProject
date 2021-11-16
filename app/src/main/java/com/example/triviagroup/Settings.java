package com.example.triviagroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Settings extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String documentId = MainActivity.documentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void selectColor(View v) {
//        Intent intent = new Intent(this, EndingScreen.class);
//        intent.putExtra("trivia name", "name");
        //System.out.println(v.getId());
//        startActivity(intent);
        String color = "White";
        String hex = "#FFFFFF";
        switch(v.getId()){
            case R.id.imageButton:
                color = "Really Red";
                hex = "#FF0000";
                break;
            case R.id.imageButton2:
                color = "Red";
                hex = "#C13030";
                break;
            case R.id.imageButton3:
                color = "Blood Red";
                hex = "#A52020";
                break;
            case R.id.imageButton4:
                color = "Crimson";
                hex = "#6F0000";
                break;
            case R.id.imageButton5:
                color = "Orange";
                hex = "#FF7000";
                break;
            case R.id.imageButton6:
                color = "Orange Cream";
                hex = "#FF9441";
                break;
            case R.id.imageButton7:
                color = "Orange Pulp";
                hex = "#E96600";
                break;
            case R.id.imageButton8:
                color = "Dark Orange";
                hex = "#CA6B20";
                break;
            case R.id.imageButton10:
                color = "New Yeller";
                hex = "#FFFF00";
                break;
            case R.id.imageButton11:
                color = "Soda Yellow";
                hex = "#FBFB00";
                break;
            case R.id.imageButton12:
                color = "Dark Yellow";
                hex = "#AAFFFF00";
                break;
            case R.id.imageButton13:
                color = "Unsaturated Yellow";
                hex = "#DEDA0A";
                break;
            case R.id.imageButton14:
                color = "Purple";
                hex = "#8B0ADE";
                break;
            case R.id.imageButton15:
                color = "Light Purple";
                hex = "#9B00FF";
                break;
            case R.id.imageButton16:
                color = "Dark Pink";
                hex = "#CFFF00FE";
                break;
            case R.id.imageButton17:
                color = "Pink";
                hex = "#FF00FE";
                break;
            case R.id.imageButton18:
                color = "bright blue";
                hex = "#00F2FF";
                break;
            case R.id.imageButton19:
                color = "lighter blue";
                hex = "#006BFF";
                break;
            case R.id.imageButton20:
                color = "blue purple";
                hex = "#1500FF";
                break;
            case R.id.imageButton21:
                color = "blue";
                hex = "#000AFF";
                break;
            case R.id.imageButton22:
                color = "Unsaturated Green";
                hex = "#CC11FF00";
                break;
            case R.id.imageButton23:
                color = "Green";
                hex = "#11FF00";
                break;
            case R.id.imageButton24:
                color = "Blueish Green";
                hex = "#00FF63";
                break;
            case R.id.imageButton25:
                color = "Yellowish Green";
                hex = "#ABFF00";
                break;
            case R.id.imageButton26:
                color = "White";
                hex = "#FFFFFF";
                break;



        }
        DocumentReference docRef = db.collection("Players").document(documentId);
        String finalColor = color;
        String finalHex = hex;
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        db.collection("Players").document(documentId)
                                .update(
                                        "bg", finalColor,
                                        "hexColor", finalHex
                                );

                    } else {
                        Log.d("DB", "No such document");
                    }
                } else {
                    Log.d("DB", "get failed with ", task.getException());
                }
            }
        });
        TextView colorC = findViewById(R.id.ColorChecker);
        colorC.setTextColor(Color.parseColor(finalHex));
        //someView.setBackgroundColor(Color.parseColor("#FFFFFF"));

    }
}
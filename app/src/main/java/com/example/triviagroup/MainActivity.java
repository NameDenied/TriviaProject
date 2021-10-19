package com.example.triviagroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}
package com.example.triviagroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class choosingTheme extends AppCompatActivity {
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_theme);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"Choose Theme","Disney", "Star Wars", "Marvel", "DC Comics", "Sports"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }


    public void startButton(View v) {
        spinner = (Spinner) findViewById(R.id.spinner1);

        Intent intent = new Intent(this, questionScreen.class);

        System.out.println(String.valueOf(spinner.getSelectedItem()));

        intent.putExtra("trivia name", String.valueOf(spinner.getSelectedItem()));

        startActivity(intent);

    }
    public void startLButton(View v) {
        Intent intent = new Intent(this, lightningScreen.class);
        System.out.println("WOWLOL");

        startActivity(intent);

    }
    public void endingClick(View v) {
        Intent intent = new Intent(this, EndingScreen.class);
        intent.putExtra("trivia name", "name");
        startActivity(intent);
    }

}
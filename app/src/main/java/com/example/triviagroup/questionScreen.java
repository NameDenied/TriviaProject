package com.example.triviagroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class questionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        Intent intent = getIntent();

        String category = intent.getStringExtra("trivia name");
        System.out.println(category);

        int index = -1;
        if(category.equals("Marvel")){
            System.out.println("INNNNNNNNNNNNNNNNN");
            index = 0;
        }
        String[][][] Questions = {{{"What is Iron Man’s real name?", "Peter Parker", "Tony Stark", "Steve Rogers"}, {"How many infinity stones does Thanos need?", "3", "4", "6"}, {"What is the main weapon used by Shang Chi and his father?", "The ten rings", "Infinity Gauntlet", "Mjollnir"}, {"How many Marvel movies have been made?", "23", "18", "25"},{"What is the setting of Black Panther?", "Australia", "Wakanda", "China", "Asgard"}, {"What is the name of Thor’s brother?", "Deadpool", "Loki", "Odin"}, {"What is Hawkeye's real name?", "Bart CLinton", "Clint Barton", "Cole Philson"}, {"What is Loki's title?", "God of Mischief", "God of Evil", "God of Tricks"}}};

        int len = Questions[index].length;
        System.out.println(len);
//        System.out.println(index);
//        System.out.println(len);
        int count = 5;
        String[] askQ = {"PC", "PC", "PC", "PC", "PC"};
        int[] useQ = {-1,-1,-1,-1,-1};

        while(count > 0){
            System.out.println(count+" "+"COUNTTTT");
            boolean found = true;
            int chosen = (int)(Math.random()*len);
            while(found){
                System.out.println(chosen+" CHOSENNNNNN");
                found = true;
                for(int i = 0; i < useQ.length; i++){
                    if(useQ[i] == chosen){
                        found = false;
                    }
                }
                if(found){
                    askQ[5-count] = Questions[index][chosen][0];
                    useQ[5-count] = chosen;
                }else{
                    chosen = (int)(Math.random()*len);
                }

            }
            count--;
        }
        for(int i = 0; i < askQ.length; i++){
            System.out.print(askQ[i] + " : " + useQ[i]);
        }
        System.out.println();
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.qspinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"Choose Choice","God", "God2", "God3", "Dora"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
}
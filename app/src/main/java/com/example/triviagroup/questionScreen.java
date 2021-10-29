package com.example.triviagroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class questionScreen extends AppCompatActivity {
    int pointsEarned = 0;
    String[] tst = {"What is life", "george", "god", "death"};
    Question[] askQ = {new Question(tst),new Question(tst),new Question(tst),new Question(tst),new Question(tst)};
    int ind = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        Intent intent = getIntent();

        String category = intent.getStringExtra("trivia name");
        TextView ThemeTextMAIN = findViewById(R.id.ThemeText);
        ThemeTextMAIN.setText(category);
        System.out.println(category);

        int index = -1;
        if(category.equals("Marvel")){
            System.out.println("INNNNNNNNNNNNNNNNN");
            index = 0;
        }
//        String[][] correctA = {{"Tony Stark", "6","The ten rings","25","Wakanda", "Loki","Clint Barton","God of Mischief"}};
        String[][] marvel = {{"What is Iron Man’s real name?", "Tony Stark", "Peter Parker", "Steve Rogers"}, {"How many infinity stones does Thanos need?", "6", "4", "3"}, {"What is the main weapon used by Shang Chi and his father?", "The ten rings", "Infinity Gauntlet", "Mjollnir"}, {"How many Marvel movies have been made?", "25", "18", "23"},{"What is the setting of Black Panther?", "Wakanda", "Australia", "China", "Asgard"}, {"What is the name of Thor’s brother?", "Loki", "Deadpool", "Odin"}, {"What is Hawkeye's real name?", "Bart CLinton", "Clint Barton", "Cole Philson"}, {"What is Loki's title?", "God of Mischief", "God of Evil", "God of Tricks"}};
        String[][] starWars = {{"What is the name of Luke Skywalker’s father?", "Anakin", "Obi-wan", "Yoda", "Qui Gon Jin"},
                {"What are the names of the empires troops?", "Clonetroopers", "Droids", "Rebels", "gunslingers"},{"How old is Yoda", "900","10000","55","105"},{"How was Obi-wan related to Luke?","Teacher","Father","Friend","Uncle"},{"What planet was Luke's Family from","Tatooine","Mustafar", "Nabu", "Earth"},{"What was the name of Luke's Apprentice that goes Rouge","Kylo Ren","Rey palpatine","Leia Skywalker", "Han Solo"},{"What was the name of the ship that destroyed Nabu?","Death Star I","Death Star II","X-wing","Tie-Fighter"},{"What are the names of the two droids that Anakin owned throughout his life?","C-3PO,R2D2","C-3PO,BB8","BB8,R2D2","C-3PO,C9R1"}};

        //String[][][] Questions = {{{"What is Iron Man’s real name?", "Peter Parker", "Tony Stark", "Steve Rogers"}, {"How many infinity stones does Thanos need?", "3", "4", "6"}, {"What is the main weapon used by Shang Chi and his father?", "The ten rings", "Infinity Gauntlet", "Mjollnir"}, {"How many Marvel movies have been made?", "23", "18", "25"},{"What is the setting of Black Panther?", "Australia", "Wakanda", "China", "Asgard"}, {"What is the name of Thor’s brother?", "Deadpool", "Loki", "Odin"}, {"What is Hawkeye's real name?", "Bart CLinton", "Clint Barton", "Cole Philson"}, {"What is Loki's title?", "God of Mischief", "God of Evil", "God of Tricks"}}};
        Question[][] Questions = {{new Question(marvel[0]), new Question(marvel[1]), new Question(marvel[2]), new Question(marvel[3]),new Question(marvel[4]),new Question(marvel[5]),new Question(marvel[6]), new Question(marvel[7])}};
        Question[] questionOrder = new Question[marvel.length];
        int len = Questions[index].length;
        System.out.println(len);
//        System.out.println(index);
//        System.out.println(len);
        int count = 5;


        int[] useQ = {-1,-1,-1,-1,-1};

//        while(count > 0){
//            System.out.println(count+" "+"COUNTTTT");
//            boolean found = true;
//            int chosen = (int)(Math.random()*len);
//            while(found){
//                System.out.println(chosen+" CHOSENNNNNN");
//                found = false;
//                for(int i = 0; i < useQ.length; i++){
//                    if(useQ[i] == chosen){
//                        found = true;
//                    }
//                }
//                if(!found){
//                    askQ[5-count] = Questions[index][chosen];
//                    useQ[5-count] = chosen;
//                }else{
//                    chosen = (int)(Math.random()*len);
//                }
//
//            }
//            count--;
//        }
        while(count > 0){
            boolean found = true;
            int chosen = (int)(Math.random()*len);
            while(found){
                found = false;
                for(int i = 0; i < useQ.length; i++){
                    if(useQ[i] == chosen){
                        found = true;
                    }
                }
                if(!found){
                    askQ[5-count] = Questions[index][chosen];
                    useQ[5-count] = chosen;
                }else{
                    chosen = (int)(Math.random()*len);
                }
            }
            count--;
        }
//        for(int i = 0; i < askQ.length; i++){
//            System.out.print(askQ[i] + " : " + useQ[i]);
//        }
//        System.out.println();
        //get the spinner from the xml.
//        String[] test = {"What is Iron Man’s real name?", "Peter Parker", "Tony Stark", "Steve Rogers"};
//        Question lol = new Question(test);

        Spinner dropdown = findViewById(R.id.qspinner);
        //create a list of items for the spinner.
//        String[] items = new String[]{Questions[index][useQ[0]][1],Questions[index][useQ[0]][2], Questions[index][useQ[0]][3], Questions[index][useQ[0]][4]};
        TextView ThemeText = findViewById(R.id.themeText);

//        for(int i = 0; i < 5; i++){
//            ThemeText.setText(askQ[0].getQuestion());
//            String[] items = new String[]{askQ[0].getAnswer()[0], askQ[0].getAnswer()[1], askQ[0].getAnswer()[2]};
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//            dropdown.setAdapter(adapter);
//            try {
//                Thread.sleep(2000); //1000 milliseconds is one second.
//            }
//            catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }
        ThemeText.setText(askQ[0].getQuestion());
        String[] items = new String[askQ[0].getAnswer().length];
        //String[] items = new String[]{askQ[0].getAnswer()[0], askQ[0].getAnswer()[1], askQ[0].getAnswer()[2], askQ[0].getAnswer()[3]};
        if(items.length == 3){
            items[0] = askQ[0].getAnswer()[0];
            items[1] = askQ[0].getAnswer()[1];
            items[2] = askQ[0].getAnswer()[2];
        }else if(items.length == 4){
            items[0] = askQ[0].getAnswer()[0];
            items[1] = askQ[0].getAnswer()[1];
            items[2] = askQ[0].getAnswer()[2];
            items[3] = askQ[0].getAnswer()[3];
        }

        System.out.println(askQ[0].getCorrectAnswer());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
//        ThemeText.setText(askQ[0].getQuestion());
//        String[] items = new String[]{askQ[0].getAnswer()[0], askQ[0].getAnswer()[1], askQ[0].getAnswer()[2]};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.

        //set the spinners adapter to the previously created one.
//        dropdown.setAdapter(adapter);
    }


    public void submitQuestion(View v){
        Spinner dropdown = findViewById(R.id.qspinner);
        String SelectedAnswer = String.valueOf(dropdown.getSelectedItem());
        System.out.println(SelectedAnswer);
        System.out.println(askQ[ind].getCorrectAnswer());
        if(askQ[ind].getCorrectAnswer().equals(SelectedAnswer)){
            System.out.println("CORRECTOMUNDO");
            pointsEarned = pointsEarned+1;
        }else{
            System.out.println("OOF YOU SUCK");
        }
        ind = ind + 1;
        TextView ThemeText = findViewById(R.id.themeText);
        ThemeText.setText(askQ[ind].getQuestion());
        String[] items = new String[askQ[ind].getAnswer().length];
        //String[] items = new String[]{askQ[0].getAnswer()[0], askQ[0].getAnswer()[1], askQ[0].getAnswer()[2], askQ[0].getAnswer()[3]};
        if(items.length == 3){
            items[0] = askQ[ind].getAnswer()[0];
            items[1] = askQ[ind].getAnswer()[1];
            items[2] = askQ[ind].getAnswer()[2];
        }else if(items.length == 4){
            items[0] = askQ[ind].getAnswer()[0];
            items[1] = askQ[ind].getAnswer()[1];
            items[2] = askQ[ind].getAnswer()[2];
            items[3] = askQ[ind].getAnswer()[3];
        }

        System.out.println(askQ[ind].getCorrectAnswer());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }
}
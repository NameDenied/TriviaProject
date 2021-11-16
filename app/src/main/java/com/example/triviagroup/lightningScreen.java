package com.example.triviagroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class lightningScreen extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String documentId = MainActivity.documentId;
    int pointsEarned = 0;
    String[] tst = {"What is life", "george", "god", "death"};
    Question[] askQ = new Question[20];
    int ind = 0;
    View ScreenView;//SET IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
    int index = -1;
    private TextView mCountDownTimerText;
    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = 60000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightning_screen);
        ImageView images = findViewById(R.id.imageView2);
        Intent intent = getIntent();


        mCountDownTimerText = findViewById(R.id.CountdownText);
//        String category = intent.getStringExtra("trivia name");
        TextView ThemeTextMAIN = findViewById(R.id.ThemeText);
        ThemeTextMAIN.setText("ALL");
//        System.out.println(category);

        TextView pointsView = findViewById(R.id.PointsView);
        DocumentReference docRef = db.collection("Players").document(documentId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        long pointsL = (long)document.get("points");
                        pointsView.setText("Points: "+pointsL);
                    } else {
                        Log.d("DB", "No such document");
                    }
                } else {
                    Log.d("DB", "get failed with ", task.getException());
                }
            }
        });
//        if(category.equals("Marvel")){
//            System.out.println("INNNNNNNNNNNNNNNNN");
//            index = 0;
//        }
//
//        else if (category.equals("Star Wars")) {
//            System.out.println("INNNNNNNNNNNNNNNNN");
//            index = 1;
//        }
//        else if (category.equals("Sports")) {
//            System.out.println("INNNNNNNNNNNNNNNNN");
//            index = 2;
//        }
//        else if (category.equals("Disney")) {
//            System.out.println("INNNNNNNNNNNNNNNNN");
//            index = 3;
//        }
//        else{
//            index = 4;
//        }


//        String[][] correctA = {{"Tony Stark", "6","The ten rings","25","Wakanda", "Loki","Clint Barton","God of Mischief"}};

        String[] marvelImages = {Integer.toString(R.drawable.ironman), Integer.toString(R.drawable.thanos), Integer.toString(R.drawable.shangchi), Integer.toString(R.drawable.marvel), Integer.toString(R.drawable.blackpanther), Integer.toString(R.drawable.thor), Integer.toString(R.drawable.hawkeye), Integer.toString(R.drawable.loki)};
        String[] starWarsImages = {Integer.toString(R.drawable.luke), Integer.toString(R.drawable.clonetrooper), Integer.toString(R.drawable.clonetrooper), Integer.toString(R.drawable.obi), Integer.toString(R.drawable.tatooine), Integer.toString(R.drawable.kylo), Integer.toString(R.drawable.deathstar), Integer.toString(R.drawable.robots)};
        String[] sportsImages = {Integer.toString(R.drawable.nba), Integer.toString(R.drawable.homerun), Integer.toString(R.drawable.covid), Integer.toString(R.drawable.superbowl), Integer.toString(R.drawable.mlb), Integer.toString(R.drawable.nbatrophy), Integer.toString(R.drawable.kareem), Integer.toString(R.drawable.wnbatrophy)};
        String[] disneyImages = {Integer.toString(R.drawable.toystory), Integer.toString(R.drawable.lionking), Integer.toString(R.drawable.cars), Integer.toString(R.drawable.nemo), Integer.toString(R.drawable.pino), Integer.toString(R.drawable.dumbo), Integer.toString(R.drawable.snowwhite), Integer.toString(R.drawable.rat)};
        String[] dcImages = {Integer.toString(R.drawable.supermanflying), Integer.toString(R.drawable.greece), Integer.toString(R.drawable.butler), Integer.toString(R.drawable.quinn), Integer.toString(R.drawable.dc), Integer.toString(R.drawable.angrysuperman), Integer.toString(R.drawable.waynes), Integer.toString(R.drawable.kordax)};
        String[][] marvel = {{"What is Iron Man’s real name?",marvelImages[0], "Tony Stark", "Peter Parker", "Steve Rogers"}, {"How many infinity stones does Thanos need?",marvelImages[1], "6", "4", "3"}, {"What is the main weapon used by Shang Chi and his father?",marvelImages[2], "The ten rings", "Infinity Gauntlet", "Mjollnir"}, {"How many Marvel movies have been made?",marvelImages[3], "25", "18", "23"},{"What is the setting of Black Panther?",marvelImages[4], "Wakanda", "Australia", "China", "Asgard"}, {"What is the name of Thor’s brother?",marvelImages[5], "Loki", "Deadpool", "Odin"}, {"What is Hawkeye's real name?",marvelImages[6], "Bart CLinton", "Clint Barton", "Cole Philson"}, {"What is Loki's title?",marvelImages[7], "God of Mischief", "God of Evil", "God of Tricks"}};
        String[][] starWars = {{"What is the name of Luke Skywalker’s father?", starWarsImages[0], "Anakin", "Obi-wan", "Yoda", "Qui Gon Jin"},
                {"What are the names of the empires troops?",starWarsImages[1], "Clonetroopers", "Droids", "Rebels", "gunslingers"},{"How old is Yoda",starWarsImages[2], "900","10000","55","105"},{"How was Obi-wan related to Luke?",starWarsImages[3],"Teacher","Father","Friend","Uncle"},{"What planet was Luke's Family from",starWarsImages[4],"Tatooine","Mustafar", "Nabu", "Earth"},{"What was the name of Luke's Apprentice that goes Rouge",starWarsImages[5],"Kylo Ren","Rey palpatine","Leia Skywalker", "Han Solo"},{"What was the name of the ship that destroyed Nabu?",starWarsImages[6],"Death Star I","Death Star II","X-wing","Tie-Fighter"},{"What are the names of the two droids that Anakin owned throughout his life?",starWarsImages[7],"C-3PO,R2D2","C-3PO,BB8","BB8,R2D2","C-3PO,C9R1"}};
        String[][] sports = {{"What does the NBA Stand for?",sportsImages[0],"National Basketball Association", "National Baseball Association", " National Badminton Academy"},{"Who holds the record for most home runs in one season?", sportsImages[1], "Barry Bonds", "Babe Ruth", "Shohei Ohtani"},{"Who was the first NBA Player to test positive for Covid-19?",sportsImages[2], "Rudy Gobert", "Donavon Mitchell", "Bojan Bodanovic"},{"Which franchise in the NFL has the most Super Bowl Wins?",sportsImages[3], "New England Patriots", "Chicago Bears", "Green Bay Packers"},{"How many teams are there in the MLB?",sportsImages[4],"30","26","20"},{"Which NBA team has the most championships of all time (As of 2019)?",sportsImages[5], "Boston Celtics", "Los Angeles Lakers","Golden State Warriors"},
                {"Who leads the NBA in all-time scoring?",sportsImages[6],"Kareem Abdul-Jabbar","Michael Jordan", "LeBron James"},{"Who won the 2021 WNBA Championship?",sportsImages[7], "Chicago Sky","Phoenix Mercury","New York Liberty"}};
        String[][] disney = {{"What is the name of Andy’s neighbor in Toy Story?",disneyImages[0],"Sid","Tom","Alex","Mike"},{"What does the famous lion king phrase, “Akunama Tatta” mean?",disneyImages[1], "“ No worries”","“ Helping others is important”", "“Living the easy life”", "“Working to Survive”"},{"In Cars 1 who wins the big race?",disneyImages[2],"Chick Hicks", "Doc Hudson", "Lightning McQueen", "Dinoco Blue"}, {"In Finding Nemo Nemo and his father are what type of fish?",disneyImages[3], "Clownfish", "Angelfish", "Sunfish", "Goldfish"}, {"In the movie Pinocchio, Pinocchio's one true dream is?",disneyImages[4], "Become a real boy", "To get rich", "Live forever", "Be the best sorcerer"},
                {" In the movie Dumbo what was Dumbo famous for?",disneyImages[5], "His ability to fly with his big ears", "His big feet", "His intelligence and ability to talk to other people", "His ability to pull huge amounts of weight"}, {"In the movie Snow white, Snow white was poisoned by which fruit?",disneyImages[6], "Apple", "Orange", "Watermelon", "Strawberry"}, {"In the movie Ratatouille Remy loves doing what most?",disneyImages[7], "Cooking food", "Scavenging for Food", "Swimming in the river", "Running for his life"}};
        String[][] dcComics ={{"What's Superman's birth name?",dcImages[0], "Kal'el", "Clark", "Kent"}, {"Which superhero was modeled after the Amazons of Ancient Greece?",dcImages[1], "Wonder Woman", "Athena", "Harley Quinn"}, {"What's the name of Batman's butler?",dcImages[2], "Alfred", "James", "Henry"}, {"To which villain is Harley Quinn attached to?",dcImages[3], "Joker", "Two-Face", "Bane"}, {"What does DC stand for?",dcImages[4], "Detective Comics", "District Comics", "Day Comics"}, {"Who is Superman's archnemesis?",dcImages[5], "Lex Luthor", "Batman", "Doomsday"}, {"Who killed Thomas and Martha Wayne?",dcImages[6], "Joe Chill", " The Joker", "The Penguin"}, {"Which superhero was born with the curse of Kordax?",dcImages[7], "Aquaman", "Wonder Woman", "Green Lantern"}};

        //String[][][] Questions = {{{"What is Iron Man’s real name?", "Peter Parker", "Tony Stark", "Steve Rogers"}, {"How many infinity stones does Thanos need?", "3", "4", "6"}, {"What is the main weapon used by Shang Chi and his father?", "The ten rings", "Infinity Gauntlet", "Mjollnir"}, {"How many Marvel movies have been made?", "23", "18", "25"},{"What is the setting of Black Panther?", "Australia", "Wakanda", "China", "Asgard"}, {"What is the name of Thor’s brother?", "Deadpool", "Loki", "Odin"}, {"What is Hawkeye's real name?", "Bart CLinton", "Clint Barton", "Cole Philson"}, {"What is Loki's title?", "God of Mischief", "God of Evil", "God of Tricks"}}};
        Question[] Questions = {new Question(marvel[0]), new Question(marvel[1]), new Question(marvel[2]), new Question(marvel[3]),new Question(marvel[4]),new Question(marvel[5]),new Question(marvel[6]), new Question(marvel[7]),
                new Question(starWars[0]), new Question(starWars[1]), new Question(starWars[2]), new Question(starWars[3]),new Question(starWars[4]),new Question(starWars[5]),new Question(starWars[6]), new Question(starWars[7]),
                new Question(sports[0]), new Question(sports[1]), new Question(sports[2]), new Question(sports[3]),new Question(sports[4]),new Question(sports[5]),new Question(sports[6]), new Question(sports[7]),
                new Question(disney[0]), new Question(disney[1]), new Question(disney[2]), new Question(disney[3]),new Question(disney[4]),new Question(disney[5]),new Question(disney[6]), new Question(disney[7]),
                new Question(dcComics[0]), new Question(dcComics[1]), new Question(dcComics[2]), new Question(dcComics[3]),new Question(dcComics[4]),new Question(dcComics[5]),new Question(dcComics[6]), new Question(dcComics[7])};
        Question[] questionOrder = new Question[marvel.length];
        int len = Questions.length;
        System.out.println(len);

//        System.out.println(index);
//        System.out.println(len);
        int count = len-20;


        int[] useQ = new int[count];

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
                    askQ[useQ.length-count] = Questions[chosen];
                    useQ[useQ.length-count] = chosen;
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
        images.setImageResource(Integer.parseInt(askQ[0].getImage()));
        ThemeText.setText(askQ[0].getQuestion());
        System.out.println(askQ[0].getImage());
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
        updateCountDownText();
        startTimer();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                Intent intent = new Intent(lightningScreen.this, choosingTheme.class);
                startActivity(intent);
//                mButtonStartPause.setText("Start");
//                mButtonStartPause.setVisibility(View.INVISIBLE);
//                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimerRunning = true;
        //mButtonStartPause.setText("pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mCountDownTimerText.setText(timeLeftFormatted);
    }



//    private void startTimer() {
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                //updateCountDownText();
//
//            }
//
//            @Override
//            public void onFinish() {
//                mTimerRunning = false;
//                mButtonStartPause.setText("Start");
//                mButtonStartPause.setVisibility(View.INVISIBLE);
//                mButtonReset.setVisibility(View.VISIBLE);
//            }
//        }.start();
//
//        mTimerRunning = true;
//        mButtonStartPause.setText("pause");
//        mButtonReset.setVisibility(View.INVISIBLE);
//    }
//    private void updateCountDownText(){
//        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//
//        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//
//        mTextViewCountDown.setText(timeLeftFormatted);
//    }

    public void submitQuestion(View v){
        ImageView images = findViewById(R.id.imageView2);

        Spinner dropdown = findViewById(R.id.qspinner);
        View someView = findViewById(R.id.mainlayout);
        String SelectedAnswer = String.valueOf(dropdown.getSelectedItem());
        System.out.println(SelectedAnswer);
        System.out.println(askQ[ind].getCorrectAnswer());
        if(askQ[ind].getCorrectAnswer().equals(SelectedAnswer)){
            System.out.println("CORRECTOMUNDO");
            pointsEarned = pointsEarned+1;
            someView.setBackgroundColor(Color.GREEN);
            //int curpoints = 0;
            //System.out.println(db.collection("Players").document(documentId));
            DocumentReference docRef = db.collection("Players").document(documentId);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            long pointsL = (long)document.get("points");
                            int points = (int)pointsL;
                            System.out.println(points);
                            db.collection("Players").document(documentId)
                                    .update(
                                            "points", points+pointsEarned
                                    );
                            TextView pointsView = findViewById(R.id.PointsView);
                            pointsView.setText("Points: "+(points+pointsEarned));
                            Log.d("DB", "DocumentSnapshot data: " + document.getData()+ document.get("points"));
                            Log.d("DB", String.valueOf(pointsL));
                        } else {
                            Log.d("DB", "No such document");
                        }
                    } else {
                        Log.d("DB", "get failed with ", task.getException());
                    }
                }
            });
            System.out.println("ASDHDOFHSW");


        }else{
            System.out.println("OOF YOU SUCK");
            someView.setBackgroundColor(Color.RED);
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        //ScreenView.setBackgroundResource(R.color.green);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                System.out.println("IT RUNSS");
                someView.setBackgroundColor(Color.WHITE);
                ind = ind + 1;
                ind = ind % askQ.length;
//                ind = Math.min(ind, 4);
                //System.out.println(askQ[ind].getImage());

                //images.setImageResource(R.drawable.askQ[ind].getImage());

                //if (ind < 1000){
                    images.setImageResource(Integer.parseInt(askQ[ind].getImage()));
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
                    dropdown.setAdapter(adapter);
                //}//else{
//                    Intent intent = new Intent(lightningScreen.this, choosingTheme.class);
//                    startActivity(intent);
                //}
            }
        }, 300);
//        System.out.println("IT RUNSSSSS222222");
    }


}

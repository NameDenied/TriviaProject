package com.example.triviagroup;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//https://github.com/DanielMartinus/Konfetti/blob/main/demo-simple-java/src/main/java/nl/dionsegijn/simple_demo/MainActivity.java

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class EndingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending_screen);
        final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
        final Shape.DrawableShape drawableShape = new Shape.DrawableShape(drawable, true);

        final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.BLACK, Color.GREEN, Color.BLUE)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000L);
    }


}



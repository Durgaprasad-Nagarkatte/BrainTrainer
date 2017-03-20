package com.company.durgaprasad.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    //ArrayList to store randomly generated answer as well as the correct answer
    ArrayList<Integer> answers;
    int locationOfCorrectAnswer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.INVISIBLE);
            }
        });

        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        //Initialising the answers ArrayList
        answers = new ArrayList<>();

        //Creating a randomNumber object of type Random
        Random randomNumber = new Random();
        //
        int a = randomNumber.nextInt(21);
        int b = randomNumber.nextInt(21);
        sumTextView.setText(Integer.toString(a) +  " + " + Integer.toString(b));

        locationOfCorrectAnswer = randomNumber.nextInt(4);
        int inCorrectAnswer;

        //for loop run for generating 3 random numbers with the correct answer
        for(int i=0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                inCorrectAnswer = randomNumber.nextInt(41);

                //T0 avoid from getting the correct answer as the random number
                while(inCorrectAnswer == a+b){
                    inCorrectAnswer = randomNumber.nextInt(41);
                }
                answers.add(inCorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
}

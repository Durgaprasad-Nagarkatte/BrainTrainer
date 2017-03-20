package com.company.durgaprasad.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.button;
import static android.R.attr.preferenceInformationStyle;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.company.durgaprasad.braintrainer.R.id.button0;
import static com.company.durgaprasad.braintrainer.R.id.sumTextView;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private RelativeLayout relative;
    private TextView pointsTextView;
    private TextView sumTextView;
    private TextView timerTextView;
    private TextView resultTextView;
    private Button playAgainButton;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;

    //ArrayList to store randomly generated answer as well as the correct answer
    ArrayList<Integer> answers;

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relative = (RelativeLayout)findViewById(R.id.relative);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.INVISIBLE);
                relative.setVisibility(View.VISIBLE);
            }
        });

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgain);
        playAgain(findViewById(R.id.playAgain));
    }

    //Method to get the button clicked and compare it with the location of the correct answer
    public void chooseAnswer(View view){
        if((view.getTag()).equals(Integer.toString(locationOfCorrectAnswer))){
            Log.i("Answer Status", "Correct Answer");
            score++;
            resultTextView.setText("Correct Answer");
        }
        else{
            Log.i("Answer Status", "Wrong Answer");
            resultTextView.setText("Wrong Answer");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void generateQuestion(){
        //Creating a randomNumber object of type Random
        Random randomNumber = new Random();
        //
        int a = randomNumber.nextInt(21);
        int b = randomNumber.nextInt(21);
        sumTextView.setText(Integer.toString(a) +  " + " + Integer.toString(b));

        //Initialising the answers ArrayList
        answers = new ArrayList<>();

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

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Done");
            }
        }.start();
    }
}

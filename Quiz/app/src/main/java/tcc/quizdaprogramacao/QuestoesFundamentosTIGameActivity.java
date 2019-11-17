package tcc.quizdaprogramacao;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;



public class QuestoesFundamentosTIGameActivity extends AppCompatActivity {
    Button buttonA, buttonB, buttonC;
    TextView questionText, triviaQuizText, timeText, resultText, coinText;
    QuestoesFundamentosTIActivity questoesFundamentosTIActivity;
    QuestoesActivity currentQuestion;
    List<QuestoesActivity> list;
    int qid = 0;
    int timeValue = 30;
    int coinValue = 0;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        questionText = (TextView) findViewById(R.id.triviaQuestion);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);

        triviaQuizText = (TextView) findViewById(R.id.triviaQuizText);
        timeText = (TextView) findViewById(R.id.timeText);
        resultText = (TextView) findViewById(R.id.resultText);
        coinText = (TextView) findViewById(R.id.contadorText);



        questoesFundamentosTIActivity = new QuestoesFundamentosTIActivity(this);
        questoesFundamentosTIActivity.getWritableDatabase();

        if (questoesFundamentosTIActivity.getAllOfTheQuestions().size() == 0) {
            questoesFundamentosTIActivity.allQuestion();
        }

        list = questoesFundamentosTIActivity.getAllOfTheQuestions();

        Collections.shuffle(list);

        currentQuestion = list.get(qid);

        countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {

                timeText.setText(String.valueOf(timeValue) + "\"");

                timeValue -= 1;

                if (timeValue == -1) {

                    resultText.setText(getString(R.string.timeup));

                    disableButton();
                }
            }

            public void onFinish() {
                timeUp();
            }
        }.start();

        updateQueAndOptions();


    }


    public void updateQueAndOptions() {

        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());



        timeValue = 30;

        countDownTimer.cancel();
        countDownTimer.start();

        coinText.setText(String.valueOf(coinValue));
        coinValue++;

    }

    public void buttonA(View view) {
        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
            if (qid < list.size() - 1) {

                disableButton();

                correctDialog();
            }
            else {

                gameWon();

            }
        }
        else {

            gameLostPlayAgain();

        }
    }

    public void buttonB(View view) {
        if (currentQuestion.getOptB().equals(currentQuestion.getAnswer())) {
            buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    public void buttonC(View view) {
        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
            buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {

            gameLostPlayAgain();
        }
    }



    public void gameWon() {
        Intent intent = new Intent(this, GanhouActivity.class);
        startActivity(intent);
        finish();
    }


    public void gameLostPlayAgain() {
        Intent intent = new Intent(this, PlayAgainActivity.class);
        startActivity(intent);
        finish();
    }


    public void timeUp() {
        Intent intent = new Intent(this, TempoActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }

    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(QuestoesFundamentosTIGameActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        onPause();


        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        Button buttonNext = (Button) dialogCorrect.findViewById(R.id.dialogNext);



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCorrect.dismiss();
                qid++;
                currentQuestion = list.get(qid);
                updateQueAndOptions();
                resetColor();
                enableButton();
            }
        });
    }

    public void resetColor() {
        buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.azul));
        buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.azul));
        buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.azul));

    }


    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);

    }

    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);

    }


}

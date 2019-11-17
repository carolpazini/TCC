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



public class QuestoesBDGameActivity extends AppCompatActivity {
    Button buttonA, buttonB, buttonC;
    TextView questionText, triviaQuizText, timeText, resultText, coinText;
    QuestoesBDActivity questoesBDActivity;
    QuestoesActivity currentQuestion;
    List<tcc.quizdaprogramacao.QuestoesActivity> list;
    int qid = 0;
    int timeValue = 30;
    int coinValue = 0;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        //Iniciando as variáveis
        questionText = (TextView) findViewById(R.id.triviaQuestion);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);

        triviaQuizText = (TextView) findViewById(R.id.triviaQuizText);
        timeText = (TextView) findViewById(R.id.timeText);
        resultText = (TextView) findViewById(R.id.resultText);
        coinText = (TextView) findViewById(R.id.contadorText);


        //puxa/liga o banco de dados
        questoesBDActivity = new QuestoesBDActivity(this);
        //permite escrever no banco de dados
        questoesBDActivity.getWritableDatabase();

        //Checa que as questões estão na tabala
        //Se não estiverem, o getAllOfTheQuestions() vai retornar zero
        if (questoesBDActivity.getAllOfTheQuestions().size() == 0) {
            questoesBDActivity.allQuestion();
        }

        //retorna as questões
        list = questoesBDActivity.getAllOfTheQuestions();

        //Mistura as questões durante o quiz para o usuário responder
        Collections.shuffle(list);

        //currentQuestion mostra a questão atual
        currentQuestion = list.get(qid);

        //cronometro
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

        //Atualiza a questão
        updateQueAndOptions();

    }


    public void updateQueAndOptions() {

        //mostra a questão e as alternativas
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());



        timeValue = 30;
        countDownTimer.cancel();
        countDownTimer.start();

        //Marca quantas questões foram acertadas até o momento
        coinText.setText(String.valueOf(coinValue));
        //acrescenta uma questão acertada ao montante existente
        coinValue++;

    }

    //Onclick pra opção A
    public void buttonA(View view) {
        //Verifica se a questão está certa, comparando com a resposta
        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
            //mostra se a questão esta correta, deixando o botao verde
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));

            if (qid < list.size() - 1) {

                //desabilita a possibilidade de clicar novamente em um botão
                disableButton();

                //Mostra se a resposta está correta
                correctDialog();
            }
            //Se o usuário respondeu todas as questões corretamente, ele é avisado que ganhou
            else {

                gameWon();

            }
        }
        //Se ele errou a questão, recebe uma mensagem que ele perdeu
        else {

            gameLostPlayAgain();

        }
    }

    //Repetimos o que fizemos para o botão A (primeira alternativa) para o botão B (segunda alternativa)
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

    //Repetimos o que fizemos para o botão A (primeira alternativa) para o botão C (terceira alternativa)
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

    //Este metodo irá navegar da atual activity para GanhouActivity
    public void gameWon() {
        Intent intent = new Intent(this, GanhouActivity.class);
        startActivity(intent);
        finish();
    }

    //Este método é chamado se o usuário erra, indo para o Jogue Novamente
    public void gameLostPlayAgain() {
        Intent intent = new Intent(this, PlayAgainActivity.class);
        startActivity(intent);
        finish();
    }

    //Este método é chamado quando o tempo acaba, indo para a activity TempoActivity, que mostra que o tempo acabou
    public void timeUp() {
        Intent intent = new Intent(this, TempoActivity.class);
        startActivity(intent);
        finish();
    }

    //Reinicia o jogo se o usuário "mudar de tela" no celular
    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    //Se a activity for destruída, o timer é cancelado
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    //Se o jogo pausar, o timer pausa também
    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    //Retorna para a tela anterior
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }

    //Dialog que aparece para o usuário se ele acerta a questão
    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(QuestoesBDGameActivity.this);
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



        //OnCLick listener para ir para a próxima questão
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogCorrect.dismiss();
                //acrescenta uma questão
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

    //Este método desabilita o botão
    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);

    }

    //Este método habilita o botão
    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);

    }


}

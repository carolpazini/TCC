package tcc.quizdaprogramacao;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class HomeScreen extends AppCompatActivity {
    FButton playGame,quit,playFundamentosTI,playJava,playMobile,playProgWeb,playRede;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //the below method will initialize views
        initViews();

        //PlayGame button - it will take you to the QuestoesBDGame
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, QuestoesBDGame.class);
                startActivity(intent);
                finish();
            }
        });

        /*playFundamentosTI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,QuestoesFundamentosTIGame.class);
                startActivity(intent);
                finish();
            }
        });

        playJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,QuestoesJavaGame.class);
                startActivity(intent);
                finish();
            }
        });


        playMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,QuestoesMobileGame.class);
                startActivity(intent);
                finish();
            }
        });

        playProgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,QuestoesProgWebGame.class);
                startActivity(intent);
                finish();
            }
        });

        playRede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,QuestoesRedeGame.class);
                startActivity(intent);
                finish();
            }
        });

        */

        //Quit button - This will quit the game
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        //initialize views here
        playGame =(FButton)findViewById(R.id.playGame);
        quit = (FButton) findViewById(R.id.quit);
        tQ = (TextView)findViewById(R.id.tQ);
        //playFundamentosTI =(FButton) findViewById(R.id.playFundamentosTI);
        //playJava =(FButton) findViewById(R.id.playJava);
        //playMobile =(FButton) findViewById(R.id.playMobile);
        //playProgWeb =(FButton) findViewById(R.id.playProgWeb);
        //playRede =(FButton) findViewById(R.id.playRede);


        //Typeface - this is for fonts style
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        quit.setTypeface(typeface);
        tQ.setTypeface(typeface);
        //playFundamentosTI.setTypeface(typeface);
        //playJava.setTypeface(typeface);
        //playMobile.setTypeface(typeface);
        //playProgWeb.setTypeface(typeface);
        //playRede.setTypeface(typeface);
    }
}

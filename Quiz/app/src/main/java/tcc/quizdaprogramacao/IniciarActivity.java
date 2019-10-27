package tcc.quizdaprogramacao;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class IniciarActivity extends AppCompatActivity {
    FButton playBD,quit,playFundamentosTI,playJava,playMobile,playProgWeb,playRede;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        //inicia as views
        initViews();

        //Vai para o tema Banco de Dados
        playBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarActivity.this, QuestoesBDGame.class);
                startActivity(intent);
                finish();
            }
        });


        //Vai para o tema Fundamentos da TI
        playFundamentosTI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarActivity.this, QuestoesFundamentosTIGame.class);
                startActivity(intent);
                finish();
            }
        });

        //Vai para o tema Java
        playJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarActivity.this, QuestoesJavaGame.class);
                startActivity(intent);
                finish();
            }
        });


        //Vai para o tema Mobile
        playMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarActivity.this, QuestoesMobileGame.class);
                startActivity(intent);
                finish();
            }
        });


        //Vai para o tema Programação Web
        playProgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarActivity.this, QuestoesProgWebGame.class);
                startActivity(intent);
                finish();
            }
        });

        //Vai para o tema Redes
        playRede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarActivity.this, QuestoesRedeGame.class);
                startActivity(intent);
                finish();
            }
        });



        //Sai do jogo
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void initViews() {
        //iniciação das views
        playBD =(FButton)findViewById(R.id.playBD);
        quit = (FButton) findViewById(R.id.quit);
        tQ = (TextView)findViewById(R.id.tQ); //Título do App
        playFundamentosTI =(FButton) findViewById(R.id.FundamentosGame);
        playJava =(FButton) findViewById(R.id.JavaGame);
        playMobile =(FButton) findViewById(R.id.MobileGame);
        playProgWeb =(FButton) findViewById(R.id.ProgWebGame);
        playRede =(FButton) findViewById(R.id.RedeGame);


        //Estilização dos tipos de Fontes
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playBD.setTypeface(typeface);
        quit.setTypeface(typeface);
        tQ.setTypeface(typeface);
        playFundamentosTI.setTypeface(typeface);
        playJava.setTypeface(typeface);
        playMobile.setTypeface(typeface);
        playProgWeb.setTypeface(typeface);
        playRede.setTypeface(typeface);




    }
}

package tcc.quizdaprogramacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {
    ImageButton playBD, playFundamentosTI, playJava, playMobile, playProgWeb, playRede;
    Button quit;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //inicia as views
        initViews();

        //Vai para o tema Banco de Dados
        playBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, QuestoesBDGameActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //Vai para o tema Fundamentos da TI
        playFundamentosTI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, QuestoesFundamentosTIGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Vai para o tema Java
        playJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, QuestoesJavaGameActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //Vai para o tema Mobile
        playMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, QuestoesMobileGameActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //Vai para o tema Programação Web
        playProgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, QuestoesProgWebGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Vai para o tema Redes
        playRede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, QuestoesRedeGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initViews() {
        //iniciação das views
        playBD = (ImageButton) findViewById(R.id.playBD);
        tQ = (TextView) findViewById(R.id.tQ); //Título do App
        playFundamentosTI = (ImageButton) findViewById(R.id.FundamentosGame);
        playJava = (ImageButton) findViewById(R.id.JavaGame);
        playMobile = (ImageButton) findViewById(R.id.MobileGame);
        playProgWeb = (ImageButton) findViewById(R.id.ProgWebGame);
        playRede = (ImageButton) findViewById(R.id.RedeGame);


    }

    public void proximaTelaConta(View view) {
        Intent intent = new Intent(this, ContaActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
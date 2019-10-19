package tcc.quizdaprogramacao;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class Tempo extends AppCompatActivity {
    FButton playAgainButton;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__up);

        playAgainButton = (FButton)findViewById(R.id.playAgainButton);
        timeUpText = (TextView)findViewById(R.id.timeUpText);

        //Botao do JOgue Novamente
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tempo.this, HomeScreen.class);
                startActivity(intent);
                finish();


            }
        });


        //Estiliza as fontes
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        timeUpText.setTypeface(typeface);
        playAgainButton.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

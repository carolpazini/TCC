package tcc.quizdaprogramacao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PlayAgain extends Activity {

    Button playAgain;
    TextView wrongAnsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);
       //Initialize
        playAgain = (Button) findViewById(R.id.playAgainButton);
        wrongAnsText = (TextView)findViewById(R.id.wrongAns);

        //Button Jogue Novamente
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgain.this, HomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //para limpar a activity
                startActivity(intent);
                finish();
            }
        });


        //Estiliza as fontes
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playAgain.setTypeface(typeface);
        wrongAnsText.setTypeface(typeface);
    }


    @Override
    public void onBackPressed() {
        finish();
    }


}

package tcc.quizdaprogramacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;



public class TempoActivity extends AppCompatActivity {
    Button playAgainButton;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempo_encerrado);

        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        timeUpText = (TextView)findViewById(R.id.timeUpText);

        //Botao do Jogue Novamente
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempoActivity.this, HomeScreenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
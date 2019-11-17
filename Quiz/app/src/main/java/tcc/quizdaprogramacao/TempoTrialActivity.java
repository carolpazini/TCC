package tcc.quizdaprogramacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;



public class TempoTrialActivity extends AppCompatActivity {
    Button cadastroButton;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_tempo_encerrado);

        cadastroButton = (Button)findViewById(R.id.cadastroButton);
        timeUpText = (TextView)findViewById(R.id.timeUpText);

        //Botao de cadastro
        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempoTrialActivity.this, CadastroActivity.class);
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
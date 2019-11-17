package tcc.quizdaprogramacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayAgainTrialActivity extends Activity {
    Button cadastroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_game_over);

        cadastroButton = (Button)findViewById(R.id.cadastroButton);

        //Botao de cadastro
        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgainTrialActivity.this, CadastroActivity.class);
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
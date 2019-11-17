package tcc.quizdaprogramacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PlayAgainActivity extends Activity {

    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        playAgain = (Button) findViewById(R.id.playAgainButton);

        //Button Jogue Novamente
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgainActivity.this, HomeScreenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //para limpar a activity
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

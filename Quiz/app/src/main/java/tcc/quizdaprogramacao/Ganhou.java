package tcc.quizdaprogramacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ganhou extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
    }

    //This is onclick listener for button
    //it will navigate from this activity to QuestoesBDGame
    public void PlayAgain(View view) {
        Intent intent = new Intent(Ganhou.this, HomeScreen.class);
        startActivity(intent);
        finish();
    }
}

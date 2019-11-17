package tcc.quizdaprogramacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GanhouActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganhou);
    }


    //Esse button manda o ganhador para a Home, para que ele possa escolher os temas do quiz
    public void PlayAgain(View view) {
        Intent intent = new Intent(GanhouActivity.this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }
}

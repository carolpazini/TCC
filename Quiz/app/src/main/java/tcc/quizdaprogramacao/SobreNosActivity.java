package tcc.quizdaprogramacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SobreNosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_nos);
    }
    public void proximaTelaLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}

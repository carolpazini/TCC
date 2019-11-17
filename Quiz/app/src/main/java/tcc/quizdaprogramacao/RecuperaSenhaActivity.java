package tcc.quizdaprogramacao;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecuperaSenhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_senha);
        //Codigo para colocar sublinhado no TextView
        TextView textView = (TextView) findViewById(R.id.txtCancelar);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }


    public void proximaTelaSenha(View view) {
        Intent intent = new Intent(this, SenhaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}

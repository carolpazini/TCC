package tcc.quizdaprogramacao;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Codigo para tela cheia
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Codigo para colocar sublinhado no TextView
        TextView textView = (TextView) findViewById(R.id.txtEsqueceuASenha);
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        TextView textView2 = (TextView) findViewById(R.id.txtCadastreseAgora);
        textView2.setPaintFlags(textView2.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

    }
    //Codigo para mudar de Activity
    public void proximaTelaTeste(View view){
        Intent intent = new Intent(this, VersaoTesteActivity.class);
        startActivity(intent);

    }

    public void proximaTelaSenha(View view){
        Intent intent = new Intent(this, SenhaActivity.class);
        startActivity(intent);
    }

    public void proximaTelaCadastro(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
}

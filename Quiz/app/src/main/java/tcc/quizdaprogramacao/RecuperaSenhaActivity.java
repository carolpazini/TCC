package tcc.quizdaprogramacao;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import tcc.quizdaprogramacao.BD.DatabaseHelper;

public class RecuperaSenhaActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_senha);


        //Codigo para mostrar a senha inserida no BD
        String PasswordStr=getIntent().getStringExtra("Password");
        TextView PasswordTV=(TextView)findViewById(R.id.txtMostraSenha);
        PasswordTV.setText(PasswordStr);

        String password=helper.MostraSenha(PasswordStr);



    }


    public void proximaTelaSenha(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}

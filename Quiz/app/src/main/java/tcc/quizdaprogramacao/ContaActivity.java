package tcc.quizdaprogramacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);

        //Codigo para mostrar os dados da conta inseridos no BD
        String UserNameStr=getIntent().getStringExtra("UserName");
        TextView UserNameTV=(TextView)findViewById(R.id.txtUsuárioConta);
        UserNameTV.setText(UserNameStr);
    }

    public void proximaTelaLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void proximaTelaSenha(View view){
        Intent intent = new Intent(this, RecuperaSenhaActivity.class);
        startActivity(intent);

    }
    public void proximaTelaIniciar(View view){
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}

package tcc.quizdaprogramacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import tcc.quizdaprogramacao.BD.DatabaseHelper;

public class SenhaActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);
    }
    public void proximaTelaRecuperaSenha(View v) {
        if (v.getId() == R.id.btnEnviar) {

            EditText EmailET = (EditText) findViewById(R.id.editEmailRecupera);
            String EmailStr = EmailET.getText().toString();
            
            String dbEmail=helper.Recupera(EmailStr);


            if (dbEmail.equals(EmailStr)) {
                Intent loginIntent = new Intent(this, RecuperaSenhaActivity.class);
                //envia dados
                loginIntent.putExtra("Password", EmailStr);

                startActivity(loginIntent);
            } else {
                Toast.makeText(this, "E-mail Inválido!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    public void proximaTelaLogin (View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

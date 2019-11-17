package tcc.quizdaprogramacao;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import tcc.quizdaprogramacao.BD.DatabaseHelper;


public class LoginActivity extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Codigo para colocar sublinhado no TextView
        TextView textView = (TextView) findViewById(R.id.txtEsqueceuASenha);
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        TextView textView2 = (TextView) findViewById(R.id.txtCadastreseAgora);
        textView2.setPaintFlags(textView2.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
    }
    //Codigo para mudar de Activity
    public void proximaTelaSenha(View view){
        Intent intent = new Intent(this, SenhaActivity.class);
        startActivity(intent);
    }

    public void proximaTelaCadastro(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void proximaTelaTrial(View view){
        Intent intent = new Intent(this, QuestoesTrialGameActivity.class);
        startActivity(intent);
    }


    public void proximaTelaTutorial(View view){
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

    public void proximaTelaSobreNos(View view){
        Intent intent = new Intent(this, SobreNosActivity.class);
        startActivity(intent);
    }

    public  void onBtnLoginInClick(View v){
        if(v.getId()==R.id.btnEntrar){
            //Colocando usuario
            EditText UserNameET=(EditText) findViewById(R.id.editUsuario);
            String UserNameStr=UserNameET.getText().toString();
            //Colocando senha
            EditText PasswordET=(EditText)findViewById(R.id.editSenhaCadastro);
            String PasswordStr=PasswordET.getText().toString();

            String dbPassword=helper.LoginIn(UserNameStr);
            //envia username para a base de dados, encontra e retorna a senha
            // compara com a senha colocada pelo usuario
            if(dbPassword.equals(PasswordStr)){
                Intent loginIntent=new Intent(this,ContaActivity.class);
                //envia os dados
                loginIntent.putExtra("UserName",UserNameStr);
                loginIntent.putExtra("Password",PasswordStr);


                startActivity(loginIntent);
            }else {
                Toast.makeText(this, "Usuário e Senha Inválidos!", Toast.LENGTH_SHORT).show();
            }


        }
    }
    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

}

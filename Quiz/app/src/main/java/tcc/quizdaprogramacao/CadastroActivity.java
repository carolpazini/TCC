package tcc.quizdaprogramacao;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tcc.quizdaprogramacao.BD.Contact;
import tcc.quizdaprogramacao.BD.DatabaseHelper;

public class CadastroActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Codigo para colocar sublinhado no TextView
        TextView textView = (TextView) findViewById(R.id.txtCancelar);
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }


    public void SignUp(View v){
        if(v.getId()==R.id.btnCadastrar){

            EditText ET_Email=(EditText) findViewById(R.id.editEmailCadastro);
            EditText ET_UserName=(EditText) findViewById(R.id.editUsuario);
            EditText ET_Password=(EditText) findViewById(R.id.editSenhaCadastro);

            String EmailStr=ET_Email.getText().toString();
            String UserNameStr=ET_UserName.getText().toString();
            String PasswordStr=ET_Password.getText().toString();
            if(PasswordStr.equals (""))
            {
                Toast.makeText(this, "Preenchimento Obrigatório!", Toast.LENGTH_SHORT).show();
            }else {
                //Insert infor to Database
                Contact contact=new Contact();
                contact.SetEmail(EmailStr);
                contact.SetUserName(UserNameStr);
                contact.SetPassword(PasswordStr);
                helper.InsertContacts(contact);

                Intent loginIntent=new Intent(this,LoginActivity.class);
                //Send Data
                loginIntent.putExtra("UserName",UserNameStr);
                loginIntent.putExtra("Password",PasswordStr);


                startActivity(loginIntent);

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

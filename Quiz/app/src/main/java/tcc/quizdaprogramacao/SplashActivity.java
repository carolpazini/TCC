package tcc.quizdaprogramacao;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    //Codigo para mudar Activity apos 5 segundos
        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        }
    }, 5000);

}
}


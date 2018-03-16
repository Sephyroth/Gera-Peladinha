package br.com.garcia.silva.leonardo.gerapeladinha;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //validando autenticaçao face
        if (AccessToken.getCurrentAccessToken() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), TelaLoginActivity.class));
        }
    }

    public void mudaTela(View view) {
        Button btn = (Button) view;
        String activity = btn.getText().toString();
        Intent intent = new Intent(this, JogadoresActivity.class);
        startActivity(intent);
    }

    public void listarJogadores(View view) {

        Button btn = (Button) view;
        String activity = btn.getText().toString();
        Intent intent = new Intent(this, JogadoresActivity.class);
        startActivity(intent);
    }

    public void listarEscalados(View view) {
        Intent intent = new Intent(this, TimesActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        startActivity(new Intent(this, TelaLoginActivity.class));
    }

    public void logOutFace(View view) {
        LoginManager.getInstance().logOut();
        startActivity(new Intent(getApplicationContext(), TelaLoginActivity.class));
    }
}

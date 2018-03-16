package br.com.garcia.silva.leonardo.gerapeladinha;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Time;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio.TimeRepositorio;
import br.com.garcia.silva.leonardo.gerapeladinha.utilitario.ConexaoDB;
import br.com.garcia.silva.leonardo.gerapeladinha.utilitario.Utilitario;

public class TimeActivity extends AppCompatActivity {

    public FloatingActionButton fabAddTime;

    public EditText edtNome;
    public ImageView imgEscudo;

    public SQLiteDatabase conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabAddTime  = findViewById(R.id.fabAddTime);

        edtNome     = findViewById(R.id.edtNome);
        imgEscudo   = findViewById(R.id.imgEscudo);

        ConexaoDB conexaoDB = new ConexaoDB();
        conexao = conexaoDB.criarConexao(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_time, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menuSalvar :
                validaCampos();
                break;

            case R.id.menuCancelar :

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void limparTela(View view) {
        finish();
        setContentView(R.layout.activity_time);
    }

    public void validaCampos() {
        if (Utilitario.isCampoVazio(edtNome.getText().toString())) {
            edtNome.requestFocus();
        }

        Time time = new Time();
        time.nome = edtNome.getText().toString();

        TimeRepositorio repositorio = new TimeRepositorio(conexao);
        repositorio.inserir(time);

    }

    public void anexarImage(View view) {
        Utilitario util = new Utilitario();

        imgEscudo.setImageBitmap(util.buscarImgGaleria());
    }
}

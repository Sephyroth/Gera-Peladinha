package br.com.garcia.silva.leonardo.gerapeladinha;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Jogador;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio.JogadorRepositorio;
import br.com.garcia.silva.leonardo.gerapeladinha.utilitario.ConexaoDB;

public class JogadorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edtNome;
    private EditText edtTelefone;
    private Spinner spnPosicao;
    private RadioButton radbtnMensal;
    private RadioButton radbtnAvulso;

    private JogadorRepositorio jogadorRepositorio;
    private ConexaoDB conexaoDB;
    private SQLiteDatabase conexao;

    private FloatingActionButton clean;

    private Jogador jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clean = findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getBaseContext(), JogadorActivity.class));
            }
        });

        edtNome         = findViewById(R.id.edtNome);
        edtTelefone     = findViewById(R.id.edtTelefone);
        spnPosicao      = findViewById(R.id.spnPosicao);
        radbtnMensal    = findViewById(R.id.radbtnMensal);
        radbtnAvulso    = findViewById(R.id.radbtnAvulso);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.posicao_array,
                R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnPosicao.setAdapter(adapter);

        conexaoDB = new ConexaoDB();
        conexao = conexaoDB.criarConexao(this);
        jogadorRepositorio = new JogadorRepositorio(conexao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_jogador, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menuSalvar:
                    comfirmar();
                break;

            case R.id.menuCancelar:
                    finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spnPosicao.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    protected void comfirmar() {
        jogador = new Jogador();

        if (!validaCampos()) try {
            jogadorRepositorio.inserir(jogador);
            finish();

        } catch (SQLException ex) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Erro");
            alert.setMessage("Falha ao inserir o jogador " + ex.getMessage());
            alert.setNeutralButton("Ok", null);
            alert.show();

            ex.printStackTrace();
        }
    }

    protected boolean validaCampos() {
        boolean res;
        String nome     = edtNome.getText().toString();
        String posicao  = spnPosicao.getSelectedItem().toString();
        String tipo     = "A";
        String telefone = edtTelefone.getText().toString();

        if (radbtnMensal.isChecked())
            tipo = "M";

        if (res = isCampoVazio(nome)) {
            edtNome.requestFocus();
        } else
        if (res = isCampoVazio(posicao)) {
            spnPosicao.requestFocus();
        } else
        if (res = isCampoVazio(telefone)) {
            edtTelefone.requestFocus();
        }

        if (res) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.alert_atecao);
            alert.setMessage(R.string.alert_message_preencha_campos);
            alert.setNeutralButton(R.string.alert_ok, null);
            alert.show();
        }

        jogador.nome        = nome;
        jogador.posicao     = posicao;
        jogador.tipo        = tipo;
        jogador.telefone    = telefone;

        return res;
    }

    public boolean isCampoVazio(String valor) {
        return (valor.isEmpty() || valor.trim().isEmpty());
    }

}

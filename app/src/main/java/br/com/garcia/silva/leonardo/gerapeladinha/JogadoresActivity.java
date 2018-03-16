package br.com.garcia.silva.leonardo.gerapeladinha;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import br.com.garcia.silva.leonardo.gerapeladinha.adpters.JogadoresAdapter;
import br.com.garcia.silva.leonardo.gerapeladinha.database.DadosOpenHelp;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Jogador;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio.JogadorRepositorio;
import br.com.garcia.silva.leonardo.gerapeladinha.utilitario.ConexaoDB;

public class JogadoresActivity extends AppCompatActivity {

    private DadosOpenHelp dadosOpenHelp;
    private ConexaoDB conexaoDB;

    private Jogador jogador;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        RecyclerView lstDados = findViewById(R.id.lstDados);

        ConexaoDB conexaoDB = new ConexaoDB();
        SQLiteDatabase conexao = conexaoDB.criarConexao(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);

        JogadorRepositorio jogadorRepositorio = new JogadorRepositorio(conexao);
        List<Jogador> dados = jogadorRepositorio.buscarTodos();
        JogadoresAdapter jogadoresAdapter = new JogadoresAdapter(dados);
        lstDados.setAdapter(jogadoresAdapter);
        lstDados.setHasFixedSize(true);

        super.onResume();
    }

    public void cadastrarJogador(View view) {
        startActivity(new Intent(this, JogadorActivity.class));
    }

    public void dadosJogador(View view) {
        TableRow linha = (TableRow) view;
        TextView linhafilho = (TextView) linha.getChildAt(0);
        String id = linhafilho.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        Intent it = new Intent(this, DadosBasicosActivity.class);
        it.putExtras(bundle);

        startActivity(it);
    }
}

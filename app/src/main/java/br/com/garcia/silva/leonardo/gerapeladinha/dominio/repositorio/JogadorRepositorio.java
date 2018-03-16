package br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Jogador;

/**
 * Created by Leonardo on 08/01/2018.
 */

public class JogadorRepositorio {

    private SQLiteDatabase conexao;

    public JogadorRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Jogador jogador) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", jogador.nome);
        contentValues.put("POSICAO", jogador.posicao);
        contentValues.put("TIPO", jogador.tipo);
        contentValues.put("TELEFONE", jogador.telefone);

        conexao.insertOrThrow("TB_JOGADOR", null, contentValues);
    }

    public void excluir(int id) {

        String[] paramentro = new String[1];
        paramentro[0] = String.valueOf(id);

        conexao.delete("TB_JOGADOR", "ID = ? ", paramentro);
    }

    public void alterar(Jogador jogador) {

        ContentValues contentValues = new ContentValues();

        if (jogador.img != null)
        contentValues.put("IMG", jogador.img);

        contentValues.put("NOME", jogador.nome);
        contentValues.put("POSICAO", jogador.posicao);
        contentValues.put("TIPO", jogador.tipo);
        contentValues.put("TELEFONE", jogador.telefone);
        contentValues.put("DATA_NASC", jogador.dataNasc);
        contentValues.put("PAGAMENTO", jogador.pagamento);
        contentValues.put("SITUACAO", jogador.situacao);

        String[] paramentro = new String[1];
        paramentro[0] = String.valueOf(jogador.id);

        conexao.update("TB_JOGADOR", contentValues, "ID = ? ", paramentro);
    }

    public List<Jogador> buscarTodos() {

        List<Jogador> jogadors = new ArrayList<Jogador>();

        Cursor resultado = conexao.rawQuery("SELECT * FROM TB_JOGADOR", null);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();

            do {
                Jogador jogador = new Jogador();
                jogador.id       = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                jogador.nome     = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                jogador.posicao  = resultado.getString(resultado.getColumnIndexOrThrow("POSICAO"));
                jogador.tipo     = resultado.getString(resultado.getColumnIndexOrThrow("TIPO"));
                jogador.telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
                jogador.situacao = resultado.getInt(resultado.getColumnIndexOrThrow("SITUACAO"));

                jogadors.add(jogador);
            } while (resultado.moveToNext());
        }

        return jogadors;
    }

    public Jogador buscarJogador(String id) {

//        String[] paramentro = new String[]{id};
//        paramentro[0] = id;

        String sql = "SELECT * FROM TB_JOGADOR WHERE ID = " + id;

        Cursor resultado = conexao.rawQuery(sql, null);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();

            Jogador jogador     = new Jogador();
            jogador.id          = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
            jogador.img         = resultado.getBlob(resultado.getColumnIndexOrThrow("IMG"));
            jogador.nome        = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
            jogador.posicao     = resultado.getString(resultado.getColumnIndexOrThrow("POSICAO"));
            jogador.tipo        = resultado.getString(resultado.getColumnIndexOrThrow("TIPO"));
            jogador.telefone    = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
            jogador.situacao    = resultado.getInt(resultado.getColumnIndexOrThrow("SITUACAO"));
            jogador.pagamento   = resultado.getInt(resultado.getColumnIndexOrThrow("PAGAMENTO"));
            jogador.dataNasc   = resultado.getString(resultado.getColumnIndexOrThrow("DATA_NASC"));

            return jogador;
        }
        return null;
    }
}

package br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Score;

/**
 * Created by Leonardo on 21/01/2018.
 */

public class ScoreRepositorio {

    private SQLiteDatabase conexao;

    public ScoreRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Score score) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("BOLA_CHEIA" , score.bolaCheia);
        contentValues.put("ID_JOGADOR" , score.paiId);
        contentValues.put("BOLA_MURCHA" , score.bolaMurcha);
        contentValues.put("CANETINHA" , score.canetinha);
        contentValues.put("CARTAO_AMARELO" , score.cartaoAmarelo);
        contentValues.put("CARTAO_VERMELHO" , score.cartaoVermelho);
        contentValues.put("CHAPEU" , score.chapeu);
        contentValues.put("DEFESA_PENALTI" , score.defesaPenalti);
        contentValues.put("GOL" , score.gol);
        contentValues.put("PASSE" , score.passe);
        contentValues.put("CLASSIFICACAO", score.pontoUltimaRodada);

        conexao.insertOrThrow("TB_SCORE", null, contentValues);
    }

    public void excluir(int id) {

        String[] paramentro = new String[] {String.valueOf(id)};

        conexao.delete("TB_SCORE", "ID = ?", paramentro);

    }

    public void alterar(Score score) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("BOLA_CHEIA" , score.bolaCheia);
        contentValues.put("BOLA_MURCHA" , score.bolaMurcha);
        contentValues.put("CANETINHA" , score.canetinha);
        contentValues.put("CARTAO_AMARELO" , score.cartaoAmarelo);
        contentValues.put("CARTAO_VERMELHO" , score.cartaoVermelho);
        contentValues.put("CHAPEU" , score.chapeu);
        contentValues.put("DEFESA_PENALTI" , score.defesaPenalti);
        contentValues.put("GOL" , score.gol);
        contentValues.put("PASSE" , score.passe);
        contentValues.put("CLASSIFICACAO", score.pontoUltimaRodada);

        conexao.update("TB_SCORE", contentValues, "ID = ?", new String[]{String.valueOf(score.id)});
    }

    public Score buscarScore(String id) {

        String sql = "SELECT * FROM TB_SCORE WHERE ID = " + 1;

        Cursor resultado = conexao.rawQuery(sql, null);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();

            Score score             = new Score();
            score.id                = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
            score.bolaCheia         = resultado.getInt(resultado.getColumnIndexOrThrow("BOLA_CHEIA"));
            score.bolaMurcha        = resultado.getInt(resultado.getColumnIndexOrThrow("BOLA_MURCHA"));
            score.canetinha         = resultado.getInt(resultado.getColumnIndexOrThrow("CANETINHA"));
            score.cartaoAmarelo     = resultado.getInt(resultado.getColumnIndexOrThrow("CARTAO_AMARELO"));
            score.cartaoVermelho    = resultado.getInt(resultado.getColumnIndexOrThrow("CARTAO_VERMELHO"));
            score.chapeu            = resultado.getInt(resultado.getColumnIndexOrThrow("CHAPEU"));
            score.defesaPenalti     = resultado.getInt(resultado.getColumnIndexOrThrow("DEFESA_PENALTI"));
            score.gol               = resultado.getInt(resultado.getColumnIndexOrThrow("GOL"));
            score.passe             = resultado.getInt(resultado.getColumnIndexOrThrow("PASSE"));
            score.pontoUltimaRodada = resultado.getInt(resultado.getColumnIndexOrThrow("CLASSIFICACAO"));

            return score;
        }

        return null;
    }
}

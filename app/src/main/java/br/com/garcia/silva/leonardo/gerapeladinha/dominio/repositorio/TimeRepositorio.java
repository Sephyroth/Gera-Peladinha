package br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Time;

/**
 * Created by Leonardo on 30/01/2018.
 */

public class TimeRepositorio {

    public SQLiteDatabase conexao;

    public TimeRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Time time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("IMG", time.img);
        contentValues.put("NOME", time.nome);

        conexao.insertOrThrow("TB_TIME", null, contentValues);
    }

    public void excluir(int id) {
        String[] paramentro = new String[] {String.valueOf(id)};

        conexao.delete("TB_TIME", "ID = ?", paramentro);
    }

    public void alterar(Time time) {
        String[] paramentro = new String[] {String.valueOf(time.id)};

        ContentValues contentValues = new ContentValues();
        contentValues.put("IMG", time.img);
        contentValues.put("NOME", time.nome);

        conexao.update("TB_TIME", contentValues, "ID = ?", paramentro);
    }

    public List<Time> buscarTodosTimes() {
        List<Time> timeList = new ArrayList<Time>();

        Cursor resposta = conexao.rawQuery("SELECT * FROM TB_TIME", null);

        if (resposta.getCount() > 0) {
            resposta.moveToFirst();

            do {
                Time time = new Time();
                time.id = resposta.getInt(resposta.getColumnIndexOrThrow("ID"));
                time.img = resposta.getBlob(resposta.getColumnIndexOrThrow("IMG"));
                time.nome = resposta.getString(resposta.getColumnIndexOrThrow("NOME"));

                timeList.add(time);

            } while (resposta.moveToNext());
        }
        return timeList;
    }
}

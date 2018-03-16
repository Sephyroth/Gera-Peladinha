package br.com.garcia.silva.leonardo.gerapeladinha.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Leonardo on 08/01/2018.
 */

public class DadosOpenHelp extends SQLiteOpenHelper {

    public DadosOpenHelp(Context context) {
        super(context, "GERAPELADINHA", null, 6);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("DROP TABLE TB_JOGADOR");
        db.execSQL(ScriptDLL.getCreateTabelaJogador());
        db.execSQL(ScriptDLL.getCreateTabelaScore());
        db.execSQL(ScriptDLL.getCreateTabelaTime());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "ALTER TABLE";
//        db.execSQL(ScriptDLL.updateTabela());
        db.execSQL(ScriptDLL.getCreateTabelaTime());
    }
}

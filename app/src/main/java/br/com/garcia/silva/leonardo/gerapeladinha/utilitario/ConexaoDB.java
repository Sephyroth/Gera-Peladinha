package br.com.garcia.silva.leonardo.gerapeladinha.utilitario;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;

import br.com.garcia.silva.leonardo.gerapeladinha.database.DadosOpenHelp;

/**
 * Created by Leonardo on 08/01/2018.
 */

public class ConexaoDB {

    private static SQLiteDatabase conexao;
    private static DadosOpenHelp dadosOpenHelp;

    public static SQLiteDatabase criarConexao(Context context) {

        try {

            dadosOpenHelp = new DadosOpenHelp(context);
            conexao = dadosOpenHelp.getWritableDatabase();
//            dadosOpenHelp.onUpgrade(conexao, 5, 6);

            return conexao;
//            Snackbar.make(layoutContentJogador, "Conexão criada com sucesso!", Snackbar.LENGTH_SHORT)
//                    .setAction("OK", null).show();
        } catch (SQLException ex) {
//            AlertDialog.Builder alert = new AlertDialog.Builder(this);
//            alert.setTitle("Erro");
//            alert.setMessage("Falha na conexao com o banco" + ex.getMessage());
//            alert.setNeutralButton("Ok", null);
//            alert.show();

            ex.printStackTrace();
        }

        return null;
    }
}

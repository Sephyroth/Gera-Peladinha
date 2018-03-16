package br.com.garcia.silva.leonardo.gerapeladinha.database;

/**
 * Created by Leonardo on 08/01/2018.
 */

public class ScriptDLL {

    public static String getCreateTabelaJogador() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_JOGADOR ( ");
        sql.append("ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("IMG BLOB, ");
        sql.append("NOME VARCHAR (250) NOT NULL  DEFAULT (''), ");
        sql.append("POSICAO VARCHAR (3) NOT NULL DEFAULT ('XX'), ");
        sql.append("TIPO VARCHAR(2) NOT NULL DEFAULT ('X'), ");
        sql.append("TELEFONE VARCHAR (20) NOT NULL DEFAULT (''), ");
        sql.append("PAGAMENTO BOOLEAN DEFAULT (0), ");
        sql.append("DATA_NASC DATE, ");
        sql.append("SITUACAO BOOLEAN DEFAULT (1) )");

        return sql.toString();
    }

    public static String getCreateTabelaScore() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_SCORE ( ");
        sql.append("ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("ID_JOGADOR INTEGER NOT NULL, ");
        sql.append("BOLA_CHEIA INTEGER, ");
        sql.append("BOLA_MURCHA INTEGER, ");
        sql.append("CANETINHA INTEGER, ");
        sql.append("CARTAO_AMARELO INTEGER, ");
        sql.append("CARTAO_VERMELHO INTEGER, ");
        sql.append("CHAPEU INTEGER, ");
        sql.append("DEFESA_PENALTI INTEGER, ");
        sql.append("GOL INTEGER,");
        sql.append("PASSE INT, ");
        sql.append("CLASSIFICACAO INTEGER, ");
        sql.append("FOREIGN KEY(ID_JOGADOR) REFERENCES TB_JOGADOR(ID))");

        return sql.toString();
    }

    public static String getCreateTabelaTime() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_TIME ( ");
        sql.append("ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("IMG BLOB, ");
        sql.append("NOME VARCHAR(50) NOT NULL)");

        return sql.toString();
    }

    public static String updateTabela() {
        StringBuilder sql = new StringBuilder();
//        sql.append("ALTER TABLE TB_JOGADOR ADD COLUMN DATA_NASC New DATE ");
//        sql.append("PAGAMENTO New BOOLEAN, ");
//        sql.append("DATA_NASC New DATE");

        return sql.toString();
    }
}

package br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Leonardo on 05/01/2018.
 */

public class Jogador {

    public int id;
    public byte[] img;
    public String nome;
    public String posicao;
    public String tipo;
    public String telefone;
    public int situacao;
    public int pagamento;
    public String dataNasc;

    public Jogador() {

    }

    public Jogador(String nome, String posicao, String tipo, String telefone) {
        this.nome       = nome;
        this.posicao    = posicao;
        this.tipo       = tipo;
        this.telefone   = telefone;
    }

//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getPosicao() {
//        return posicao;
//    }
//
//    public void setPosicao(String posicao) {
//        this.posicao = posicao;
//    }
//
//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
//
//    public String getTelefone() {
//        return telefone;
//    }
//
//    public void setTelefone(String telefone) {
//        this.telefone = telefone;
//    }
//
//    public String getSituacao() {
//        return situacao;
//    }
//
//    public void setSituacao(String situacao) {
//        this.situacao = situacao;
//    }

    public String toIdParaString() {
        return String.valueOf(this.id);
    }

    public boolean toIntParaBoolean(int valor) {
        if(valor == 1)
            return true;
        return false;
    }

    public String toDataParaString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        simpleDateFormat.format(date);

        return simpleDateFormat.toString();
    }
}

package br.com.garcia.silva.leonardo.gerapeladinha.adpters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import br.com.garcia.silva.leonardo.gerapeladinha.R;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Jogador;

/**
 * Created by Leonardo on 08/01/2018.
 */

public class JogadoresAdapter extends RecyclerView.Adapter<JogadoresAdapter.ViewHolderJogadores> {

    private List<Jogador> dados;

    public JogadoresAdapter(List<Jogador> dados) {
        this.dados = dados;
    }

    @Override
    public JogadoresAdapter.ViewHolderJogadores onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_jogadores, parent, false);

        ViewHolderJogadores viewHolderJogadores = new ViewHolderJogadores(view);

        return viewHolderJogadores;
    }

    @Override
    public void onBindViewHolder(JogadoresAdapter.ViewHolderJogadores holder, int position) {

        if (dados != null && dados.size() > 0) {
            Jogador jogador = dados.get(position);

            if (position % 2 == 0)
                holder.tbrLinha.setBackgroundColor(Color.GRAY);

            holder.txtId.setText(jogador.toIdParaString());
            holder.txtNome.setText(jogador.nome);
            holder.txtPosicao.setText(jogador.posicao);
            holder.txtTipo.setText(jogador.tipo);
            holder.txtTelefone.setText(jogador.telefone);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderJogadores extends RecyclerView.ViewHolder {

        public TextView txtId;
        public TextView txtNome;
        public TextView txtPosicao;
        public TextView txtTipo;
        public TextView txtTelefone;
        public TableRow tbrLinha;

        public ViewHolderJogadores(View itemView) {
            super(itemView);

            txtId       = (TextView) itemView.findViewById(R.id.txtId);
            txtNome     = (TextView) itemView.findViewById(R.id.txtNome);
            txtPosicao  = (TextView) itemView.findViewById(R.id.txtPosicao);
            txtTipo     = (TextView) itemView.findViewById(R.id.txtTipo);
            txtTelefone = (TextView) itemView.findViewById(R.id.txtTelefone);
            tbrLinha    = (TableRow) itemView.findViewById(R.id.tbrLinha);
        }
    }
}

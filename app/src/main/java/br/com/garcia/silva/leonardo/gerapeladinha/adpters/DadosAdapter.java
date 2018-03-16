package br.com.garcia.silva.leonardo.gerapeladinha.adpters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import br.com.garcia.silva.leonardo.gerapeladinha.R;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Jogador;

/**
 * Created by Leonardo on 12/01/2018.
 */

public class DadosAdapter extends RecyclerView.Adapter<DadosAdapter.ViewHolderDados> {

    private Jogador jogador;
    private Context contexto;

    public DadosAdapter(Context contexto, Jogador jogador) {
        this.contexto = contexto;
        this.jogador = jogador;
    }

    @Override
    public DadosAdapter.ViewHolderDados onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_dados, parent, false);

        ViewHolderDados viewHolderDados = new ViewHolderDados(view);

        return viewHolderDados;
    }

    @Override
    public void onBindViewHolder(DadosAdapter.ViewHolderDados holder, int position) {

        if (jogador != null) {


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(contexto, R.array.posicao_array,
                R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        holder.spnPosicao.setAdapter(arrayAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(contexto, R.array.tipo_array,
                R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        holder.spnTipo.setAdapter(adapter);

            holder.id.setText(jogador.toIdParaString());
            holder.img.setImageResource(R.drawable.imgperfil);
            holder.edtNome.setText(jogador.nome);
            holder.edtTelefone.setText(jogador.telefone);
            holder.tgbPagamento.setChecked(jogador.toIntParaBoolean(jogador.pagamento));
            holder.tgbSituacao.setChecked(jogador.toIntParaBoolean(jogador.situacao));

            if (jogador.img != null) {
                Bitmap raw  = BitmapFactory.decodeByteArray(jogador.img,0,jogador.img.length);
                holder.img.setImageBitmap(raw);
            }
            if (jogador.situacao == 0) {
                holder.tgbSituacao.setChecked(false);
                holder.tgbSituacao.setBackgroundResource(R.color.colorAccent);
            }
            if (jogador.pagamento == 0) {
                holder.tgbPagamento.setChecked(false);
                holder.tgbPagamento.setBackgroundResource(R.color.colorAccent);
            }
            if (jogador.dataNasc != null) {
                holder.edtDataNasc.setText(jogador.dataNasc);
            }
            int index = 0;
            for (String item : holder.posicao) {
                if (item.contains(jogador.posicao))
                    holder.spnPosicao.setSelection(index);
                index++;
            }

            index = 0;
            for (String item : holder.tipo) {
                if (item.contains(jogador.tipo))
                    holder.spnTipo.setSelection(index);
                index++;
            }
        }
    }

    public int pegaPosicaoArray(DadosAdapter.ViewHolderDados holder, String item) {

        for (int i = 0; i < holder.posicao.length; i++) {
            String valor = holder.posicao[i];
            if (valor.contains(item))
                return i;
        }

        return 0;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolderDados extends RecyclerView.ViewHolder {

        public TextView id;
        public ImageView img;
        public EditText edtNome;
        public EditText edtDataNasc;
        public EditText edtTelefone;
        public ToggleButton tgbPagamento;
        public ToggleButton tgbSituacao;
        public Spinner spnPosicao;
        public Spinner spnTipo;

        public String[] posicao;
        public String[] tipo;

        public ViewHolderDados(View itemView) {
            super(itemView);

            posicao = itemView.getResources().getStringArray(R.array.posicao_array);
            tipo = itemView.getResources().getStringArray(R.array.tipo_array);

            id              = (TextView) itemView.findViewById(R.id.txtId);
            img             = (ImageView) itemView.findViewById(R.id.imgUsuario);
            edtNome         = (EditText) itemView.findViewById(R.id.edtNome);
            edtDataNasc     = (EditText) itemView.findViewById(R.id.edtDataNasc);
            edtTelefone     = (EditText) itemView.findViewById(R.id.edtTelefone);
            tgbPagamento    = (ToggleButton) itemView.findViewById(R.id.tgbPagamento);
            tgbSituacao     = (ToggleButton) itemView.findViewById(R.id.tgbSituacao);
            spnTipo         = (Spinner) itemView.findViewById(R.id.spnTipo);
            spnPosicao      = (Spinner) itemView.findViewById(R.id.spnPosicao);
        }
    }
}

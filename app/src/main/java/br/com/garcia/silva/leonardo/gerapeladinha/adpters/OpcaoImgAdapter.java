package br.com.garcia.silva.leonardo.gerapeladinha.adpters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.garcia.silva.leonardo.gerapeladinha.R;

/**
 * Created by Leonardo on 02/03/2018.
 */

public class OpcaoImgAdapter extends BaseAdapter {

    private List<String> opcaoes;
    private Activity act;

    public void OpcaoImgAdapter(List opcaoes, Activity act) {
        this.opcaoes = opcaoes;
        this.act     = act;
    }

    @Override
    public int getCount() {
        return opcaoes.size();
    }

    @Override
    public Object getItem(int position) {
        return opcaoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.dialogo_camera, parent, false);
        String opcao = opcaoes.get(position);

        TextView txtCamera = (TextView) view.findViewById(R.id.txtFoto);
        TextView txtGaleria = (TextView) view.findViewById(R.id.txtGaleria);
        TextView txtDelete = (TextView) view.findViewById(R.id.txtDelete);

        txtCamera.setText(opcao);
        txtGaleria.setText(opcao);
        txtDelete.setText(opcao);

        return view;

    }
}

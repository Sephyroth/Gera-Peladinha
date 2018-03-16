package br.com.garcia.silva.leonardo.gerapeladinha.adpters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.garcia.silva.leonardo.gerapeladinha.R;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Time;

/**
 * Created by Leonardo on 30/01/2018.
 */

public class TimesAdapter extends Adapter<TimesAdapter.ViewHolderTimes> {

    public List<Time> timeList;

    public TimesAdapter(List<Time> timeList) {
        this.timeList = timeList;
    }

    @Override
    public TimesAdapter.ViewHolderTimes onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_times, parent, false);

        ViewHolderTimes viewHolderTimes = new ViewHolderTimes(view);

        return viewHolderTimes;
    }

    @Override
    public void onBindViewHolder(final TimesAdapter.ViewHolderTimes holder, int position) {

        if (timeList != null && timeList.size()> 0) {
            Time time = timeList.get(position);

//            Bitmap bitmap = BitmapFactory.decodeByteArray(time.img, 0, time.img.length);
//            holder.imgEscudo.setImageBitmap(bitmap);
            holder.txtNome.setText(time.nome);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.txtNome.setText("deu certo com ");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class ViewHolderTimes extends RecyclerView.ViewHolder {

        public TextView txtId;
        public ImageView imgEscudo;
        public TextView txtNome;
        public View view;

        public ViewHolderTimes(View itemView) {
            super(itemView);

            view = itemView;

//            txtId = (TextView) itemView.findViewById(R.id.)
            imgEscudo = (ImageView) itemView.findViewById(R.id.imgEscudo);
            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
        }
    }
}


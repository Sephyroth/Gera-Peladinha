package br.com.garcia.silva.leonardo.gerapeladinha.adpters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import br.com.garcia.silva.leonardo.gerapeladinha.ItemFragment.OnListFragmentInteractionListener;
import br.com.garcia.silva.leonardo.gerapeladinha.R;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Score;
import br.com.garcia.silva.leonardo.gerapeladinha.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private List<Score> scores;
    private final OnListFragmentInteractionListener mListener;

    public ScoreAdapter(List<Score> dados, OnListFragmentInteractionListener listener) {
        scores = dados;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);
        final Score score = scores.get(position);

        if (scores.size() > 0) {

            holder.bolaCheia.setText(String.valueOf(score.bolaCheia));
            holder.bolaMurcha.setText(String.valueOf(score.bolaMurcha));
            holder.canetinha.setText(String.valueOf(score.canetinha));
            holder.cartaoAmarelo.setText(String.valueOf(score.cartaoAmarelo));
            holder.cartaoVermelho.setText(String.valueOf(score.cartaoVermelho));
            holder.chapeu.setText(String.valueOf(score.chapeu));
            holder.defesaPenalti.setText(String.valueOf(score.defesaPenalti));
            holder.gol.setText(String.valueOf(score.gol));
            holder.passe.setText(String.valueOf(score.passe));
            holder.pontoUltimaRodada.setNumStars(score.pontoUltimaRodada);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(score);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView bolaCheia;
        public TextView bolaMurcha;
        public TextView canetinha;
        public TextView cartaoAmarelo;
        public TextView cartaoVermelho;
        public TextView chapeu;
        public TextView defesaPenalti;
        public TextView gol;
        public TextView passe;
        public RatingBar pontoUltimaRodada;
        public View mView;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            bolaCheia = (TextView) view.findViewById(R.id.txtBolaCheia);
            bolaMurcha = (TextView) view.findViewById(R.id.txtBolaMurcha);
            canetinha = (TextView) view.findViewById(R.id.txtCanetinha);
            cartaoAmarelo = (TextView) view.findViewById(R.id.txtCartaoAmarelo);
            cartaoVermelho = (TextView) view.findViewById(R.id.txtCartaoVermelho);
            chapeu = (TextView) view.findViewById(R.id.txtChapeu);
            defesaPenalti = (TextView) view.findViewById(R.id.txtDefesaPenalti);
            gol = (TextView) view.findViewById(R.id.txtGol);
            passe = (TextView) view.findViewById(R.id.txtPasse);
            pontoUltimaRodada = (RatingBar) view.findViewById(R.id.pontoUltimaRodada);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}

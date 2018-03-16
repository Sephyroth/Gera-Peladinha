package br.com.garcia.silva.leonardo.gerapeladinha;

import android.app.AlertDialog;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.garcia.silva.leonardo.gerapeladinha.adpters.ScoreAdapter;
import br.com.garcia.silva.leonardo.gerapeladinha.database.DadosOpenHelp;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Score;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio.ScoreRepositorio;
import br.com.garcia.silva.leonardo.gerapeladinha.dummy.DummyContent;
import br.com.garcia.silva.leonardo.gerapeladinha.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private String id;
    private List<Score> score;

    private DadosOpenHelp dadosOpenHelp;
    private SQLiteDatabase conexao;

    private RecyclerView recyclerViewScore;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            id = getArguments().getString("id");

            score = new ArrayList<Score>();
            criaConexao();
            ScoreRepositorio scoreRepositorio = new ScoreRepositorio(conexao);
            score.add(scoreRepositorio.buscarScore(id));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.score, container, false);

        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new ScoreAdapter(DummyContent.ITEMS, mListener));
//        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        ScoreAdapter scoreAdapter = new ScoreAdapter(score, mListener);

        recyclerViewScore = view.findViewById(R.id.recyclerRiewScore);
        recyclerViewScore.setLayoutManager(linearLayoutManager);
        recyclerViewScore.setAdapter(scoreAdapter);
        recyclerViewScore.setHasFixedSize(true);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
            Toast.makeText(context, "Trocou para tela Score", Toast.LENGTH_LONG).show();
//            criarConexao();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Score item);
    }

    public void criaConexao() {

        try {
            dadosOpenHelp = new DadosOpenHelp(getContext());
            conexao = dadosOpenHelp.getWritableDatabase();
//            dadosOpenHelp.onUpgrade(conexao, 5, 6);
        } catch (SQLException ex) {
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("Erro");
            alert.setMessage("Falha na conexao com o banco" + ex.getMessage());
            alert.setNeutralButton("Ok", null);
            alert.show();

            ex.printStackTrace();
        }
    }
}

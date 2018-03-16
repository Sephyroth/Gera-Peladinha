package br.com.garcia.silva.leonardo.gerapeladinha;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;

import br.com.garcia.silva.leonardo.gerapeladinha.adpters.DadosAdapter;
import br.com.garcia.silva.leonardo.gerapeladinha.database.DadosOpenHelp;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Jogador;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio.JogadorRepositorio;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DadosPessoasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DadosPessoasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DadosPessoasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_ID = "id";

    private FrameLayout frameLayoutDadosPessoais;
    private Bitmap imageBitmap;
    private ImageView img;
    private EditText edtNome;
    private EditText edtDataNasc;
    private EditText edtTelefone;
    private ToggleButton tgbPagamento;
    private ToggleButton tgbSituacao;
    private Spinner spnPosicao;
    private Spinner spnTipo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String id;

    private Jogador jogador;
    private JogadorRepositorio jogadorRepositorio;

    private DadosOpenHelp dadosOpenHelp;
    private SQLiteDatabase conexao;

    private RecyclerView recyclerViewDados;

    private OnFragmentInteractionListener mListener;

    public DadosPessoasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DadosPessoasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DadosPessoasFragment newInstance(String param1, String param2) {
        DadosPessoasFragment fragment = new DadosPessoasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_ID);
            jogadorRepositorio = new JogadorRepositorio(conexao);
            jogador = new Jogador();
            jogador = jogadorRepositorio.buscarJogador(id);
        }

//        Bitmap imageBitmap = (Bitmap) getArguments().get("data");
//
//        if (imageBitmap != null) {
//            img.setImageBitmap(imageBitmap);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_pessoas, container, false);


//        spnPosicao = (Spinner) view.findViewById(R.id.spnPosicao);
//        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.posicao_array,
//                R.layout.support_simple_spinner_dropdown_item);
//        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        spnPosicao.setAdapter(arrayAdapter);
//
//        spnTipo = (Spinner) view.findViewById(R.id.spnTipo);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipo_array,
//                R.layout.support_simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        spnTipo.setAdapter(adapter);

        recyclerViewDados = (RecyclerView) view.findViewById(R.id.recycleViewDados);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewDados.setLayoutManager(layoutManager);


//        Bitmap imageBitmap = (Bitmap) getArguments().get("data");
//
//        if (imageBitmap != null) {
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            jogador.img = stream.toByteArray();
//        }


        DadosAdapter dadosAdapter = new DadosAdapter(getContext(), jogador);
        recyclerViewDados.setAdapter(dadosAdapter);
        recyclerViewDados.setHasFixedSize(true);

        frameLayoutDadosPessoais = (FrameLayout) view.findViewById(R.id.frameLayoutDadosPessoais);
//        img = (ImageView) view.findViewById(R.id.img);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dispatchTakePictureIntent(v);
//            }
//        });
//        edtNome = (EditText) view.findViewById(R.id.edtNome);
//        edtDataNasc = (EditText) view.findViewById(R.id.edtDataNasc);
//        edtTelefone = (EditText) view.findViewById(R.id.edtTelefone);
//        tgbPagamento = (ToggleButton) view.findViewById(R.id.tgbPagamento);
//        tgbSituacao = (ToggleButton) view.findViewById(R.id.tgbSituacao);
//        spnTipo = (Spinner) view.findViewById(R.id.spnTipo);
//        spnPosicao = (Spinner) view.findViewById(R.id.spnPosicao);


//        Bundle args = getArguments();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//            Snackbar.make(frameLayoutDadosPessoais, "Trocou para tela Dados pessoais", Snackbar.LENGTH_SHORT).
//                    setAction("OK", null).show();
            Toast.makeText(context, "Trocou para tela Dados pessoais", Toast.LENGTH_LONG).show();
            criarConexao();
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }

    public void criarConexao() {

        try {

            dadosOpenHelp = new DadosOpenHelp(getContext());
            conexao = dadosOpenHelp.getWritableDatabase();
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

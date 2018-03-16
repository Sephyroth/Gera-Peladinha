package br.com.garcia.silva.leonardo.gerapeladinha;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.PermissionRequest;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import br.com.garcia.silva.leonardo.gerapeladinha.database.DadosOpenHelp;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Jogador;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio.JogadorRepositorio;
import br.com.garcia.silva.leonardo.gerapeladinha.utilitario.ConexaoDB;
import br.com.garcia.silva.leonardo.gerapeladinha.utilitario.Utilitario;

public class DadosBasicosActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private android.support.v4.app.FragmentManager fragment = getSupportFragmentManager();
    private android.support.v4.app.FragmentTransaction transaction;
    private Bundle bundle;

    private ConstraintLayout constraintLayoutDialogCamera;

    private DadosPessoasFragment myFrag;

    private Bitmap imageBitmap;
    private TextView id;
    private ImageView img;
    private EditText edtNome;
    private EditText edtDataNasc;
    private EditText edtTelefone;
    private ToggleButton tgbPagamento;
    private ToggleButton tgbSituacao;
    private Spinner spnPosicao;
    private Spinner spnTipo;

    private RecyclerView recyclerViewDados;

    private Jogador jogador;
    private JogadorRepositorio jogadorRepositorio;

    private SQLiteDatabase conexao;
    private DadosOpenHelp dadosOpenHelp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            fragment = getSupportFragmentManager();
            transaction = fragment.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    transaction.replace(R.id.containerLayout,  myFrag).commit();
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    ItemFragment itemFragment = new ItemFragment();
                    itemFragment.setArguments(bundle);
                    transaction.replace(R.id.containerLayout, itemFragment).commit();
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_basicos);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        bundle = intent.getExtras();

        String id = bundle.getString("id");

        myFrag = new DadosPessoasFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("username", user_ame);
//        bundle.putString("senha", user_pass);
        myFrag.setArguments(bundle);

        fragment = getSupportFragmentManager();
        transaction = fragment.beginTransaction();
        transaction.replace(R.id.containerLayout, myFrag).commit();

        ConexaoDB conexaoDB = new ConexaoDB();
        conexao = conexaoDB.criarConexao(this);

        // codigo para nao dar foco no editext
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        recyclerViewDados = (RecyclerView) findViewById(R.id.recycleViewDados);

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                final int PERMISSAO_REQUEST = 2;
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
            }
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        img = (ImageView) view.findViewById(R.id.imgUsuario);

    }

    final int PICK_IMAGE = 1234;
    public void pegarImagemGaleria(View view) {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            Matrix matrix = new Matrix();

            matrix.postRotate(10);

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageBitmap,200,250,true);

            Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);
            img.setImageBitmap(rotatedBitmap);
        } else {
            if(resultCode != Activity.RESULT_CANCELED) {
                if (requestCode == PICK_IMAGE) {
                    Uri selectedImage = data.getData();
//                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(selectedImage);
//                    Toast.makeText(getApplicationContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
//                    Bundle extras = data.getExtras();
//                    imageBitmap = (Bitmap) extras.get("data");
                    String path = getImagePath(selectedImage);
                    imageBitmap = BitmapFactory.decodeFile(path);

                    img.setImageBitmap(imageBitmap);
                }
            }
        }
    }

//    pegar caminho de uma image atravez da uri
    public String getImagePath(Uri contentUri) {
        String[] campos = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, campos, null, null, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_dados, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menuSalvar:
                confirmar();
                break;

            case R.id.menuCancelar:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public boolean validaCampos() {

        boolean res = false;

        String nome     = edtNome.getText().toString();
        String posicao  = spnPosicao.getSelectedItem().toString();
        String tipo     = spnTipo.getSelectedItem().toString();
        String telefone = edtTelefone.getText().toString();
        String idSstring       = id.getText().toString();

        if (res = Utilitario.isCampoVazio(nome)) {
            edtNome.requestFocus();
        } else
            if (res = Utilitario.isCampoVazio(telefone)) {
                edtTelefone.requestFocus();
            }

        if (res) {
            android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
            alert.setTitle("Atenção");
            alert.setMessage("Preencha todos os campos para cadastrar um jogador");
            alert.setNeutralButton("Ok", null);
            alert.show();
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (imageBitmap != null) {
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            jogador.img = byteArray;
        } else {
//            imageBitmap = ((BitmapDrawable)img.getBackground()).getBitmap();
//            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            jogador.img = byteArray;
        }

        jogador.id          = Integer.parseInt(idSstring);
        jogador.nome        = nome;
        jogador.dataNasc    = edtDataNasc.getText().toString();
        jogador.pagamento   = 0;
        jogador.situacao    = 0;
        jogador.posicao     = spnPosicao.getSelectedItem().toString();
        jogador.tipo        = spnTipo.getSelectedItem().toString();
        jogador.telefone    = edtTelefone.getText().toString();

        if (tgbPagamento.isChecked())
            jogador.pagamento   = 1;

        if (tgbSituacao.isChecked())
            jogador.situacao   = 1;

        return res;
    }

    public void confirmar() {

        jogador = new Jogador();

        id      = (TextView) findViewById(R.id.txtId);
        img     = (ImageView) findViewById(R.id.imgUsuario);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtDataNasc = (EditText) findViewById(R.id.edtDataNasc);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        tgbPagamento = (ToggleButton) findViewById(R.id.tgbPagamento);
        tgbSituacao = (ToggleButton) findViewById(R.id.tgbSituacao);
        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        spnPosicao = (Spinner) findViewById(R.id.spnPosicao);


        if (validaCampos() == false) {
            jogadorRepositorio = new JogadorRepositorio(conexao);
            jogadorRepositorio.alterar(jogador);
        }
    }

    public void dialogo(View view) {

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Titulo");
//        builder.setView(R.layout.dialogo_camera);
//        builder.create();
//        builder.show();
        img = (ImageView) findViewById(R.id.imgUsuario);
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialogo_camera);
//
//        dialog.show();
//        setContentView(R.layout.dialogo_camera);
//        View view1 = (View) view.getRootView();
//        constraintLayoutDialogCamera = view1.findViewById(R.id.constraintLayoutDialogCamera);
//        constraintLayoutDialogCamera.setVisibility(view.VISIBLE);

        AlertDialog.Builder getImageFrom = new AlertDialog.Builder(this);
        final CharSequence[] opsChars = {getResources().getString(R.string.txt_camera), getResources().getString(R.string.txt_galeria)};
        getImageFrom.setItems(opsChars, new android.content.DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }else
                if(which == 1){
                    Intent i = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE);
                }
                dialog.dismiss();
            }
        });
        getImageFrom.create();
        getImageFrom.show();
    }
}

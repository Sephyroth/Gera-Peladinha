package br.com.garcia.silva.leonardo.gerapeladinha;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.List;

import br.com.garcia.silva.leonardo.gerapeladinha.adpters.TimesAdapter;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.entidades.Time;
import br.com.garcia.silva.leonardo.gerapeladinha.dominio.repositorio.TimeRepositorio;
import br.com.garcia.silva.leonardo.gerapeladinha.utilitario.ConexaoDB;

public class TimesActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    public FloatingActionButton fabTime;
    public RecyclerView recyclerViewTime;

    public SQLiteDatabase conexao;

    public TimesAdapter timesAdapter;
    public TimeRepositorio timeRepositorio;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabTime = (FloatingActionButton) findViewById(R.id.fabTime);
        recyclerViewTime = (RecyclerView) findViewById(R.id.recycleViewTime);

        //validando autenticaçao face
        if (AccessToken.getCurrentAccessToken() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), TelaLoginActivity.class));
        }

        //validando autenticaçao google
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,  this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    @Override
    protected void onResume() {

        ConexaoDB conexaoDB = new ConexaoDB();
        conexao = conexaoDB.criarConexao(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewTime.setLayoutManager(layoutManager);

        timeRepositorio = new TimeRepositorio(conexao);
        List<Time> timeList = timeRepositorio.buscarTodosTimes();

        timesAdapter = new TimesAdapter(timeList);

        recyclerViewTime.setAdapter(timesAdapter);
        recyclerViewTime.setHasFixedSize(true);
        super.onResume();
    }

    public void cadastrarTime(View view) {
        startActivity(new Intent(this, TimeActivity.class));
    }

    //metodos para verificar conta google
    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            String nome = account.getDisplayName();
            String email = account.getEmail();
            String id = account.getId();
            ImageView photoImageView = findViewById(R.id.photoImageView);

//            Log.d("handleSignInResult: ", account.getPhotoUrl().toString());
            Glide.with(this).load(account.getPhotoUrl()).into(photoImageView);
        } else {
            finish();
            startActivity(new Intent(getApplicationContext(), TelaLoginActivity.class));
        }

    }

    public void logOut(View view) {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    startActivity(new Intent(getApplicationContext(), TelaLoginActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Não foi possivel desconectar da conta google", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void revogarAcesso(View view) {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    startActivity(new Intent(getApplicationContext(), TelaLoginActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Não foi possivel desconectar da conta google", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

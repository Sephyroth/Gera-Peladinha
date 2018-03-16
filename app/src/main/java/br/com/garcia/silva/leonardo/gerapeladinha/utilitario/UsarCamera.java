package br.com.garcia.silva.leonardo.gerapeladinha.utilitario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Leonardo on 12/01/2018.
 */

public class UsarCamera {

    public void carregarFoto(String nomeArquivoFoto, ImageView ivFoto) {
        // Local onde será buscado a foto a ser carregada na ImageView
        // Environment.DIRECTORY_PICTURES pega o diretório Pictures do Android
        String local = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/"
                + nomeArquivoFoto;

        File file = new File(local);
        if(file.exists())
        {
            // Cria uma imagem a partir de um caminho onde se encontrar um arquivo
            Bitmap imagem = BitmapFactory.decodeFile(local);
            // Altera um imagemView para uma nova imagem, neste caso a imagem com o
            // caminho especificado acima
            ivFoto.setImageBitmap(imagem);
        }
    }

    public Intent capturarFoto(String nomeArquivoFoto) {
        // Cria uma intenção de Capturar de Imagem ou seja, usar a camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Environment.DIRECTORY_PICTURES pega o diretório Pictures do Android
        // Usaremos este local para armazenar as imagens
        File picsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(picsDir, nomeArquivoFoto);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile)); // Arquivo
        // a ser
        // armezado
        // Inicia a captura da imagem
        return intent;
    }
}

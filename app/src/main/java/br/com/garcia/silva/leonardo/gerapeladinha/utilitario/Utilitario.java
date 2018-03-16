package br.com.garcia.silva.leonardo.gerapeladinha.utilitario;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Leonardo on 17/01/2018.
 */

public class Utilitario {

    public static boolean isCampoVazio(String valor) {

        boolean resultado = (valor.isEmpty() || valor.trim().isEmpty());

        return resultado;
    }

    final int REQUEST_IMAGE_GALERIA = 1;
    public Bitmap imageBitmap = null;
    public Bitmap buscarImgGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), REQUEST_IMAGE_GALERIA);

        return imageBitmap;
    }

    private Bitmap startActivityForResult(Intent chooser, int request_image_galeria) {
        if(request_image_galeria != Activity.RESULT_CANCELED) {
            if (request_image_galeria == REQUEST_IMAGE_GALERIA) {
//                    Uri selectedImage = data.getData();
//                    Toast.makeText(getApplicationContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
                Bundle extras = chooser.getExtras();
                imageBitmap = (Bitmap) extras.get("data");

                return imageBitmap;
            }
        }
        return imageBitmap;
    }
}

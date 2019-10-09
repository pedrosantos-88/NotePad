package com.example.pedro.notepad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
     private ImageView mImageViewSave;
     private static final String ARQ_TXT = "anotaçao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.editText);
        mImageViewSave = findViewById(R.id.imageViewSave);

        mImageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textodigitado = mEditText.getText().toString();
                gravarArquivo(textodigitado);
                Toast.makeText(MainActivity.this , "Saved",Toast.LENGTH_SHORT).show();
            }

         });

        if (lerArquivo() != null){
            mEditText.setText(lerArquivo());

        }



    }

    private void gravarArquivo(String textodigitado) {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(ARQ_TXT,Context.MODE_PRIVATE));
            outputStreamWriter.write(textodigitado);
            outputStreamWriter.close();
        }catch (IOException e){
            Log.v("MainAct" , e.toString());

        }
    }

    private String lerArquivo(){
        String resultado = "" ;

        try {

            //abrir arquivo
            InputStream arquivo = openFileInput(ARQ_TXT);
            if (arquivo!= null) {
                //ler aqruivo
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                //gerar buffer
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                ///Recuear texto
                String linhaArquivo = "";

                while ((linhaArquivo = bufferedReader.readLine()) != null) {
                    resultado += linhaArquivo;


                }
                arquivo.close();
            }

        }catch (IOException e) {
            Log.v("main" , e.toString());
        }

        return resultado;
    }
}

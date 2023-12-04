package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class Habilidades extends AppCompatActivity {
    String habs;
    int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);
        habs = "";
        contador = 0;
    }
    public void seleccionados(View vista) {
        //La línea de código CheckBox cb = (CheckBox) vista;
        // se utiliza para obtener una referencia a un objeto CheckBox
        // a partir de la vista que ha sido pasada como argumento al método seleccionados(View vista).

        CheckBox cb = (CheckBox) vista;
        if (cb.isChecked()) {
            if (contador < 1) {
                habs += cb.getText().toString() + ", ";
                cb.setEnabled(false);
                contador++;
            } else {
                habs += cb.getText().toString() + " y ";
                cb.setEnabled(false);
                contador++;
            }
        }
        //Estas líneas de código eliminan la coma y el espacio adicionales al final de la cadena habs
        StringBuilder sb = new StringBuilder(habs);
        sb.deleteCharAt(sb.length() - 2);

        if (contador == 3) {
            Intent intent = new Intent();
            intent.putExtra("habilidades", sb.toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}




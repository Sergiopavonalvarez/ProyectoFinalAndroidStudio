package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class stats1 extends AppCompatActivity {

    EditText fuerza;
    EditText destreza;
    EditText constitucion;
    EditText inteligencia;
    EditText sabiduria;
    EditText carisma;

    int[] aleatorios;
    int contador;
    int[] resultados;
    int resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats1);
        fuerza = findViewById(R.id.fuerza);
        destreza = findViewById(R.id.destreza);
        constitucion = findViewById(R.id.constitucion);
        inteligencia = findViewById(R.id.inteligencia);
        sabiduria = findViewById(R.id.sabiduria);
        carisma = findViewById(R.id.carisma);
        aleatorios = new int[]{1, 2, 3, 4, 5, 6};
        contador = 0;
        resultados = new int[6];
        resultado = 0;
    }

    public void generarnumeros(View vista) {
        Button b = (Button) vista;
        //La línea if (vista.getId() == R.id.btnFuerza) se utiliza para verificar si la vista que
        // ha sido clicada corresponde al botón de fuerza (btnFuerza).
        if (vista.getId() == R.id.btnFuerza) {
            int num1 = (int) (Math.random() * aleatorios[5]);
            int num2 = (int) (Math.random() * aleatorios[5]);
            int num3 = (int) (Math.random() * aleatorios[5]);
            resultado = num1 + num2 + num3;
            fuerza.setText(String.valueOf(resultado));
            resultados[0] = resultado;
            b.setEnabled(false);
            b.setVisibility(View.INVISIBLE);
            contador++;
        }
        if (vista.getId() == R.id.btnDestreza) {
            int num1 = (int) (Math.random() * aleatorios[5]);
            int num2 = (int) (Math.random() * aleatorios[5]);
            int num3 = (int) (Math.random() * aleatorios[5]);
            resultado = num1 + num2 + num3;
            destreza.setText(String.valueOf(resultado));
            resultados[1] = resultado;
            b.setEnabled(false);
            //La línea b.setVisibility(View.INVISIBLE); se utiliza para hacer invisible
            // el botón al que hace referencia la variable b.
            b.setVisibility(View.INVISIBLE);
            contador++;
        }
        if (vista.getId() == R.id.btnConstitucion) {
            int num1 = (int) (Math.random() * aleatorios[5]);
            int num2 = (int) (Math.random() * aleatorios[5]);
            int num3 = (int) (Math.random() * aleatorios[5]);
            resultado = num1 + num2 + num3;
            constitucion.setText(String.valueOf(resultado));
            resultados[2] = resultado;
            b.setEnabled(false);
            b.setVisibility(View.INVISIBLE);
            contador++;
        }
        if (vista.getId() == R.id.btnInteligencia) {
            int num1 = (int) (Math.random() * aleatorios[5]);
            int num2 = (int) (Math.random() * aleatorios[5]);
            int num3 = (int) (Math.random() * aleatorios[5]);
            resultado = num1 + num2 + num3;
            inteligencia.setText(String.valueOf(resultado));
            resultados[3] = resultado;
            b.setEnabled(false);
            b.setVisibility(View.INVISIBLE);
            contador++;
        }
        if (vista.getId() == R.id.btnSabiduria) {
            int num1 = (int) (Math.random() * aleatorios[5]);
            int num2 = (int) (Math.random() * aleatorios[5]);
            int num3 = (int) (Math.random() * aleatorios[5]);
            resultado = num1 + num2 + num3;
            sabiduria.setText(String.valueOf(resultado));
            resultados[4] = resultado;
            b.setEnabled(false);
            b.setVisibility(View.INVISIBLE);
            contador++;
        }
        if (vista.getId() == R.id.btnCarisma) {
            int num1 = (int) (Math.random() * aleatorios[5]);
            int num2 = (int) (Math.random() * aleatorios[5]);
            int num3 = (int) (Math.random() * aleatorios[5]);
            resultado = num1 + num2 + num3;
            carisma.setText(String.valueOf(resultado));
            resultados[5] = resultado;
            b.setEnabled(false);
            b.setVisibility(View.INVISIBLE);
            contador++;
        }

        if (contador == 6) {
            Intent intent = new Intent();
            intent.putExtra("stats", resultados);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
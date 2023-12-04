package com.example.proyectofinal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner sp;
    Button habilidades;
    Button stats1;
    String habs;
    String nombre;
    PersonajesDBHelper dbh;
    EditText personaje1;
    int[] datos;
    String spSeleccionado;
    String[] personajes = {"Bardo", "Bárbaro", "Brujo", "Clérigo", "Druida", "Explorador", "Guerrero", "Hechicero", "Mago", "Monje", "Paladín", "Pícaro"};
    int [] clases ={R.drawable.barbaro,R.drawable.bardo,R.drawable.brujo,R.drawable.clerigo,R.drawable.druida,R.drawable.explorado,R.drawable.guerrero,R.drawable.hechicero,R.drawable.mago,R.drawable.monje,R.drawable.paladin,R.drawable.picaro};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = findViewById(R.id.spinner);
        habilidades = findViewById(R.id.btnHabilidades);
        stats1 = findViewById(R.id.btnStats);
        sp = findViewById(R.id.spinner);
        personaje1 = findViewById(R.id.nombrePresonaje);
        clasesAdapter adaptador1 = new clasesAdapter();
        sp.setAdapter(adaptador1);
        nombre = getIntent().getStringExtra("nombre");
        dbh = new PersonajesDBHelper(this, "PERSONAJESDND2", null, 1);
        datos = new int[6];
        spSeleccionado = "";
        //Una clase interna que extiende BaseAdapter para personalizar la apariencia del Spinner.
        //cuando se selecciona un elemento en el Spinner, se almacene el nombre de la clase seleccionada en la variable spSeleccionado
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spSeleccionado = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        //este bloque de código maneja el resultado de una actividad que devuelve habilidades como un extra. Extrae esas habilidades,
        // las muestra en un Toast y desactiva el botón asociado a la acción para evitar repeticiones innecesarias.
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    habs = data.getStringExtra("habilidades");
                    Toast.makeText(getApplicationContext(), habs + " ", Toast.LENGTH_LONG).show();
                    //establece la propiedad de habilitación/deshabilitación del botón habilidades. Al llamar a setEnabled(false)
                    habilidades.setEnabled(false);
                }
            }
        }
    });
    ActivityResultLauncher<Intent> startForResult1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        //Extrae esas estadísticas, las almacena en una variable y desactiva el botón asociado a la acción para evitar repeticiones innecesarias.
        @Override
        public void onActivityResult(ActivityResult result1) {
            if (result1.getResultCode() == RESULT_OK) {
                Intent data = result1.getData();
                if (data != null) {
                    datos = data.getIntArrayExtra("stats");
                    stats1.setEnabled(false);
                }
            }
        }
    });
    //En resumen, este bloque de código se encarga de insertar los datos del personaje en la base
    // de datos y notificar sobre el resultado mediante mensajes Toast y el uso de setResult para
    // informar a la actividad que la inició.
    public void GuardarPersonaje(View vista) {
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE_JUGADOR", nombre);
        values.put("NOMBRE_PERSONAJE", personaje1.getText().toString());
        values.put("CLASE", spSeleccionado);
        values.put("FUERZA", datos[0]);
        values.put("DESTREZA", datos[1]);
        values.put("CONSTITUCION", datos[2]);
        values.put("INTELIGENCIA", datos[3]);
        values.put("SABIDURIA", datos[4]);
        values.put("CARISMA", datos[5]);
        values.put("HABILIDADES", habs);
        long resultado = db.insert("PERSONAJESDND2", null, values);

        if (resultado != -1) {
            Toast.makeText(getApplicationContext(), "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("fin","" );
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Error al insertar datos. Cambie el nombre del personaje", Toast.LENGTH_SHORT).show();
        }
    }
    public void cambiarActivityHbilidaddes(View view) {
        Intent b;
        b = new Intent(this, Habilidades.class);
        startForResult.launch(b);
    }
    public void cambiarActivityStats(View view) {
        Intent b;
        b = new Intent(this, stats1.class);
        startForResult1.launch(b);
    }
    //Esta clase clasesAdapter actúa como un adaptador personalizado para el Spinner,
    // permitiendo que cada elemento se presente de una manera específica.
    // En este caso, cada elemento del Spinner consiste en una imagen (ImageView) y un texto (TextView).
    // La imagen y el texto se obtienen de los arrays clases y personajes, respectivamente.
    public class clasesAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return clases.length;
        }

        @Override
        public Object getItem(int position) {
            return personajes[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            convertView = inflater.inflate(R.layout.itemspinner, null);
            ImageView iv1 = convertView.findViewById(R.id.imageView);
            TextView tv1 = convertView.findViewById(R.id.txtclase);
            iv1.setImageResource(clases[position]);
            tv1.setText(personajes[position]);
            return convertView;
        }
    }
}
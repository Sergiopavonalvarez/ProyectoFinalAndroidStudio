package com.example.proyectofinal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText dd;
    Button personaje1;

    //dbh es una instancia de la clase PersonajesDBHelper, que es una clase para manejar la base de
    // datos SQLite, según el código proporcionado anteriormente.
    PersonajesDBHelper dbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dd = findViewById(R.id.nombre);
        personaje1 = findViewById(R.id.personaje);
        //dbh = new PersonajesDBHelper(this, "PERSONAJESDND2", null, 1);: Crea una instancia de
        // PersonajesDBHelper para manejar la base de datos. La base de datos se llama "PERSONAJESDND2", y se especifica la versión como 1.
        dbh = new PersonajesDBHelper(this, "PERSONAJESDND2", null, 1);
    }
    // Crea un lanzador de actividad para manejar el resultado de otra actividad que se iniciará más adelante.
    //new ActivityResultContracts.StartActivityForResult(): Define el contrato de inicio de actividad. En este caso,
    //se utiliza el contrato estándar para iniciar una actividad y obtener un resultado.
    //new ActivityResultCallback<ActivityResult>() { ... }: Define un callback que se ejecutará cuando se reciba el resultado de la actividad.
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == RESULT_OK) {
                dd.setText("");
            }
        }
    });
    public void cambiarActivity(View view) {

        String nombre = dd.getText().toString();
        if (nombre.length() > 0) {
            Intent b = new Intent(this, MainActivity.class);
            b.putExtra("nombre", dd.getText().toString());
            startForResult.launch(b);
        }else{
            Toast.makeText(getApplicationContext(),"Introduce un nombre por favor ", Toast.LENGTH_LONG).show();
        }
    }
}

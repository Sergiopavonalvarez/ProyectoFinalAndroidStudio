package com.example.proyectofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PersonajesDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "personajes.db";
    private static final int DATABASE_VERSION = 1;

    public PersonajesDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q1 = "CREATE TABLE personajesdnd2 (" +
                "nombre_jugador VARCHAR(200), " +
                "nombre_personaje VARCHAR(200), " +
                "clase VARCHAR(200), " +
                "fuerza INTEGER, " +
                "destreza INTEGER, " +
                "constitucion INTEGER, " +
                "inteligencia INTEGER, " +
                "sabiduria INTEGER, " +
                "carisma INTEGER, " +
                "habilidades TEXT, " +
                "PRIMARY KEY (nombre_jugador, nombre_personaje)" +
                ");";

        db.execSQL(q1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

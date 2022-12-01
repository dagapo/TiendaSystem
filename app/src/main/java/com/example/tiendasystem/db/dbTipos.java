package com.example.tiendasystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbTipos extends DbHelper{

    Context context;
    public dbTipos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Cursor mostrarTipos()
    {
        try{
            SQLiteDatabase bd = this.getReadableDatabase();
            Cursor filas = bd.rawQuery("SELECT * FROM "+ TABLE_TIPOS +"", null);
            if(filas.moveToFirst())
            {
                return filas;

            }
            else {
                return null;
            }
        }catch (Exception ex)
        {
            return null;
        }
    }
    public long insertarTipos(String nombre){
        long id =0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);

            id = db.insert(TABLE_TIPOS, null, values );
        }catch (Exception ex)
        {
            ex.toString();
        }
        return id;
    }

}

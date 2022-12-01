package com.example.tiendasystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbUsuarios extends DbHelper{

    Context context;
    public dbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String nombre, String contrasena){
        long id =0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("contrasena", contrasena);

            id = db.insert(TABLE_USUARIOS, null, values );
        }catch (Exception ex)
        {
            ex.toString();
        }
        return id;
    }
}

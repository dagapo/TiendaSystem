package com.example.tiendasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiendasystem.db.DbHelper;
import com.example.tiendasystem.db.dbTipos;

public class tiposCrear extends AppCompatActivity {
    Button btn2;
    EditText editText1;
    String nombre;

    private Cursor fila;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_crear);
        btn2 = findViewById(R.id.button2);

        editText1 = findViewById(R.id.editText1);

        DbHelper dbHelper = new DbHelper(tiposCrear.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper admin=new DbHelper(tiposCrear.this);
                SQLiteDatabase db=admin.getWritableDatabase();
                nombre = editText1.getText().toString();
                fila=db.rawQuery("select * from t_tipos where nombre='"+
                        nombre+"'",null);
                try {
                    if(fila.moveToFirst()){
                        String nom=fila.getString(1);
                        if (nombre.equals(nom)){
                            Toast.makeText(tiposCrear.this, "¡El tipo ya existente!", Toast.LENGTH_SHORT).show();
                            editText1.setText("");
                        }
                    }
                    else {
                        if(!nombre.equals("")){
                            dbTipos dbTipos = new dbTipos(tiposCrear.this);
                            long id = dbTipos.insertarTipos(editText1.getText().toString());
                            if(id > 0){
                                Toast.makeText(tiposCrear.this, "¡Se ha registrado correctamente!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(tiposCrear.this, "¡Ha ocurrido algo inesperado!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                } catch (Exception e) {
                    Toast toast=Toast.makeText(tiposCrear.this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
}
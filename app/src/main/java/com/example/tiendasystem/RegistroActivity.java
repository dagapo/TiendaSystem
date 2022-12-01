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
import com.example.tiendasystem.db.dbUsuarios;

public class RegistroActivity extends AppCompatActivity {
    Button btn1, btn2;
    EditText editText1, editText2;
    String usuario, contrasena;
    private Cursor fila;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        DbHelper dbHelper = new DbHelper(RegistroActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper admin=new DbHelper(RegistroActivity.this);
                SQLiteDatabase db=admin.getWritableDatabase();
                usuario = editText1.getText().toString();
                contrasena = editText2.getText().toString();
                fila=db.rawQuery("select * from t_usuarios where nombre='"+
                        usuario+"'",null);
                try {
                    if(fila.moveToFirst()){
                        String usua=fila.getString(1);
                        if (usuario.equals(usua)){
                            Toast.makeText(RegistroActivity.this, "¡Usuario ya existente!", Toast.LENGTH_SHORT).show();
                            editText1.setText("");
                            editText2.setText("");
                        }
                    }
                    else {
                        if(!usuario.equals("")){
                            dbUsuarios dbUsuarios = new dbUsuarios(RegistroActivity.this);
                            long id = dbUsuarios.insertarUsuario(editText1.getText().toString(), editText2.getText().toString());
                            if(id > 0){
                                Toast.makeText(RegistroActivity.this, "¡Se ha registrado correctamente!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(RegistroActivity.this, "¡Ha ocurrido algo inesperado!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                } catch (Exception e) {
                    Toast toast=Toast.makeText(RegistroActivity.this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
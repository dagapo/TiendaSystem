package com.example.tiendasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tiendasystem.db.DbHelper;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    EditText editText1, editText2;
    String userlogged;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SharedPreferences prefe = getSharedPreferences("session", Context.MODE_PRIVATE);
        userlogged = prefe.getString("user", "");
        if (!(userlogged.length()==0)){
            Intent intent = new Intent(MainActivity.this,  MenuActivity.class);
            startActivity(intent);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper admin=new DbHelper(MainActivity.this);
                SQLiteDatabase db=admin.getWritableDatabase();
                String usuario=editText1.getText().toString();
                String contrasena=editText2.getText().toString();
                if(!usuario.equals("")){
                    fila=db.rawQuery("select nombre,contrasena from t_usuarios where nombre='"+
                            usuario+"' and contrasena='"+contrasena+"'",null);
                    try {

                        if(fila.moveToFirst()){
                            String usua=fila.getString(0);
                            String pass=fila.getString(1);
                            if (usuario.equals(usua)&&contrasena.equals(pass)){
                                Intent intent = new Intent(MainActivity.this,  MenuActivity.class);
                                SharedPreferences preferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("user", usuario);
                                editor.commit();
                                startActivity(intent);
                                editText1.setText("");
                                editText2.setText("");
                            }
                        }
                        else {
                            Toast toast=Toast.makeText(MainActivity.this,"¡Datos incorrectos!",Toast.LENGTH_LONG);
                            toast.show();
                        }

                    } catch (Exception e) {
                        Toast toast=Toast.makeText(MainActivity.this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
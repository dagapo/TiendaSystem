package com.example.tiendasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiendasystem.db.DbHelper;

public class productoEliminar extends AppCompatActivity {
    Button btn1;
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_eliminar);
        btn1 = findViewById(R.id.button1);
        et1 = findViewById(R.id.txtCodigo);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper admin=new DbHelper(productoEliminar.this);
                SQLiteDatabase db=admin.getWritableDatabase();

                db.execSQL("DELETE FROM t_productos where id_producto= '"+et1.getText()+"'");
                Toast.makeText(productoEliminar.this, "¡Se ha eliminado correctamente!", Toast.LENGTH_SHORT).show();
                finish();
                db.close();
            }
        });
    }
}
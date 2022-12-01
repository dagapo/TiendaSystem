package com.example.tiendasystem.db.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendasystem.R;
import com.example.tiendasystem.db.entidades.productos;

import java.util.ArrayList;

public class listaProductos extends RecyclerView.Adapter<listaProductos.ProductoViewHolder>{
    ArrayList<productos> listaProductos;

    public  listaProductos(ArrayList<productos> listaProductos){
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public listaProductos.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto,null,false);

        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaProductos.ProductoViewHolder holder, int position) {
        holder.viewCodigo.setText("Codigo: " + listaProductos.get(position).getId());
        holder.viewNombre.setText("Nombre: " + listaProductos.get(position).getNombre());
        holder.viewPrecio.setText("Precio: " + String.valueOf(listaProductos.get(position).getPrecio()));
        holder.viewTipo.setText("Categoria: " + String.valueOf(listaProductos.get(position).getNombretipo()));
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewPrecio, viewTipo, viewCodigo;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewCodigo = itemView.findViewById(R.id.txtCodigo);
            viewNombre = itemView.findViewById(R.id.txtNombre);
            viewPrecio = itemView.findViewById(R.id.txtPrecio);
            viewTipo = itemView.findViewById(R.id.txtCategoria);

        }
    }
}

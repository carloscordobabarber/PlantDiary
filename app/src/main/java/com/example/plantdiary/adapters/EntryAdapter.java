package com.example.plantdiary.adapters;

// EntryAdapter.java

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.plantdiary.R;
import com.example.plantdiary.models.PlantEntry;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private List<PlantEntry> entradas;
    private Context context;

    public EntryAdapter(Context context, List<PlantEntry> entradas) {
        this.context = context;
        this.entradas = entradas;
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_plant_entry, parent, false);
        return new EntryViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        PlantEntry entrada = entradas.get(position);
        holder.txtNombre.setText(entrada.getNombre());
        holder.txtFrecuencia.setText("Riego: " + entrada.getFrecuenciaRiego());

        try {
            Uri uri = Uri.parse(entrada.getImagenUri());
            holder.imgPlanta.setImageURI(uri);
        } catch (Exception e) {
            holder.imgPlanta.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtFrecuencia;
        ImageView imgPlanta;

        public EntryViewHolder(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtFrecuencia = itemView.findViewById(R.id.txtFrecuencia);
            imgPlanta = itemView.findViewById(R.id.imgPlanta);
        }
    }
}

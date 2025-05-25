// EntryAdapter.java
package com.example.plantdiary.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.example.plantdiary.R;
import com.example.plantdiary.models.PlantEntry;
import com.example.plantdiary.storage.EntryStorage;

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
        holder.txtFrecuencia.setText("Riego: cada " + entrada.getFrecuenciaRiego() + " días");
        holder.txtAgua.setText("Agua: " + entrada.getCantidadAgua());
        holder.txtSol.setText("Sol: " + entrada.getCantidadSol());
        holder.txtComentarios.setText("Notas: " + entrada.getComentarios());


        Uri uri = Uri.parse(entrada.getImagenUri());
        Glide.with(context)
                .load(uri)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgPlanta);

        holder.btnEliminar.setOnClickListener(v -> {
            entradas.remove(position);
            EntryStorage.eliminarEntrada(context, position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, entradas.size());
        });
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtFrecuencia, txtAgua, txtSol, txtComentarios;
        ImageView imgPlanta;
        ImageButton btnEliminar;

        public EntryViewHolder(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtFrecuencia = itemView.findViewById(R.id.txtFrecuencia);
            txtAgua = itemView.findViewById(R.id.txtAgua);
            txtSol = itemView.findViewById(R.id.txtSol);
            txtComentarios = itemView.findViewById(R.id.txtComentarios);
            imgPlanta = itemView.findViewById(R.id.imgPlanta);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}

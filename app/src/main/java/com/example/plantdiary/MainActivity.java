package com.example.plantdiary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// MainActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantdiary.adapters.EntryAdapter;
import com.example.plantdiary.storage.EntryStorage;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerEntradas;
    private Button btnNuevaEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerEntradas = findViewById(R.id.recyclerEntradas);
        btnNuevaEntrada = findViewById(R.id.btnNuevaEntrada);

        recyclerEntradas.setLayoutManager(new LinearLayoutManager(this));
        actualizarLista();

        btnNuevaEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewEntryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarLista(); // actualiza la lista al volver de agregar entrada
    }

    private void actualizarLista() {
        EntryAdapter adapter = new EntryAdapter(this, EntryStorage.obtenerEntradas());
        recyclerEntradas.setAdapter(adapter);
    }
}

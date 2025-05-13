// NewEntryActivity.java
package com.example.plantdiary;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plantdiary.models.PlantEntry;
import com.example.plantdiary.storage.EntryStorage;

public class NewEntryActivity extends AppCompatActivity {

    private EditText etNombre, etFrecuencia, etComentarios, etImagenUri;
    private Spinner spinnerAgua;
    private RadioGroup radioGroupSol;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        etNombre = findViewById(R.id.etNombre);
        etFrecuencia = findViewById(R.id.etFrecuenciaRiego);
        etComentarios = findViewById(R.id.etComentarios);
        etImagenUri = findViewById(R.id.etImagenUri);
        spinnerAgua = findViewById(R.id.spinnerCantidadAgua);
        radioGroupSol = findViewById(R.id.radioGroupSol);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Opciones para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_agua, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgua.setAdapter(adapter);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String frecuencia = etFrecuencia.getText().toString();
            String cantidadAgua = spinnerAgua.getSelectedItem().toString();

            String cantidadSol = "";
            int checkedId = radioGroupSol.getCheckedRadioButtonId();
            if (checkedId != -1) {
                RadioButton radio = findViewById(checkedId);
                cantidadSol = radio.getText().toString();
            }

            String comentarios = etComentarios.getText().toString();
            String imagenUri = etImagenUri.getText().toString();

            PlantEntry nuevaEntrada = new PlantEntry(nombre, frecuencia, cantidadAgua, cantidadSol, comentarios, imagenUri);
            EntryStorage.agregarEntrada(nuevaEntrada);

            finish(); // volver a MainActivity
        });
    }
}

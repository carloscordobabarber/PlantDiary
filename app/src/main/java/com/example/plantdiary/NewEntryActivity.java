// NewEntryActivity.java
package com.example.plantdiary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plantdiary.models.PlantEntry;
import com.example.plantdiary.storage.EntryStorage;

public class NewEntryActivity extends AppCompatActivity {

    private EditText etNombre, etFrecuencia, etComentarios;
    private Spinner spinnerAgua;
    private RadioGroup radioGroupSol;
    private Button btnGuardar, btnSeleccionarImagen;
    private ImageView previewImagen;

    private Uri imagenSeleccionadaUri = null;

    // Lanzador de la galería
    private final ActivityResultLauncher<Intent> selectorImagenLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imagenSeleccionadaUri = result.getData().getData();
                    previewImagen.setImageURI(imagenSeleccionadaUri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        etNombre = findViewById(R.id.etNombre);
        etFrecuencia = findViewById(R.id.etFrecuenciaRiego);
        etComentarios = findViewById(R.id.etComentarios);
        spinnerAgua = findViewById(R.id.spinnerCantidadAgua);
        radioGroupSol = findViewById(R.id.radioGroupSol);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        previewImagen = findViewById(R.id.previewImagen);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_agua, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgua.setAdapter(adapter);

        btnSeleccionarImagen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            selectorImagenLauncher.launch(intent);
        });

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
            String imagenUri = (imagenSeleccionadaUri != null) ? imagenSeleccionadaUri.toString() : "";

            PlantEntry nuevaEntrada = new PlantEntry(nombre, frecuencia, cantidadAgua, cantidadSol, comentarios, imagenUri);
            EntryStorage.agregarEntrada(this, nuevaEntrada);

            finish();
        });
    }
}

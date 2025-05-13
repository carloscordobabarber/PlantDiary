package com.example.plantdiary.storage;

// EntryStorage.java

import com.example.plantdiary.models.PlantEntry;
import java.util.ArrayList;
import java.util.List;

public class EntryStorage {
    private static List<PlantEntry> entradas = new ArrayList<>();

    public static void agregarEntrada(PlantEntry entry) {
        entradas.add(entry);
    }

    public static List<PlantEntry> obtenerEntradas() {
        return new ArrayList<>(entradas); // Devolvemos una copia para proteger los datos internos
    }
}

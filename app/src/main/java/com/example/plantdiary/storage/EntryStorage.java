package com.example.plantdiary.storage;

// EntryStorage.java

import com.example.plantdiary.models.PlantEntry;
import java.util.ArrayList;
import java.util.List;
// EntryStorage.java

import android.content.Context;
import android.content.SharedPreferences;

import com.example.plantdiary.models.PlantEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EntryStorage {
    private static final String PREF_NAME = "plant_diary_prefs";
    private static final String KEY_ENTRADAS = "entradas_guardadas";
    private static List<PlantEntry> entradas = new ArrayList<>();

    public static void cargarDesdePreferencias(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_ENTRADAS, null);
        if (json != null) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<PlantEntry>>(){}.getType();
            entradas = gson.fromJson(json, tipoLista);
        } else {
            entradas = new ArrayList<>();
        }
    }

    public static void guardarEnPreferencias(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(entradas);
        editor.putString(KEY_ENTRADAS, json);
        editor.apply();
    }

    public static void agregarEntrada(Context context, PlantEntry entry) {
        entradas.add(entry);
        guardarEnPreferencias(context);
    }

    public static List<PlantEntry> obtenerEntradas() {
        return new ArrayList<>(entradas);
    }
    public static void eliminarEntrada(Context context, int index) {
        if (index >= 0 && index < entradas.size()) {
            entradas.remove(index);
            guardarEnPreferencias(context);
        }
    }

}

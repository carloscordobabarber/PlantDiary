package com.example.plantdiary.models;
// PlantEntry.java

public class PlantEntry {
    private String nombre;
    private String frecuenciaRiego;
    private String cantidadAgua;
    private String cantidadSol;
    private String comentarios;
    private String imagenUri; // puede ser una URI de galería o nombre de archivo drawable

    public PlantEntry(String nombre, String frecuenciaRiego, String cantidadAgua, String cantidadSol, String comentarios, String imagenUri) {
        this.nombre = nombre;
        this.frecuenciaRiego = frecuenciaRiego;
        this.cantidadAgua = cantidadAgua;
        this.cantidadSol = cantidadSol;
        this.comentarios = comentarios;
        this.imagenUri = imagenUri;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getFrecuenciaRiego() { return frecuenciaRiego; }
    public String getCantidadAgua() { return cantidadAgua; }
    public String getCantidadSol() { return cantidadSol; }
    public String getComentarios() { return comentarios; }
    public String getImagenUri() { return imagenUri; }

    // Setters (si los necesitas)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFrecuenciaRiego(String frecuenciaRiego) { this.frecuenciaRiego = frecuenciaRiego; }
    public void setCantidadAgua(String cantidadAgua) { this.cantidadAgua = cantidadAgua; }
    public void setCantidadSol(String cantidadSol) { this.cantidadSol = cantidadSol; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
    public void setImagenUri(String imagenUri) { this.imagenUri = imagenUri; }
}

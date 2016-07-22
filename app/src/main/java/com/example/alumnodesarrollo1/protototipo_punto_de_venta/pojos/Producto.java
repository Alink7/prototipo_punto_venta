package com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos;

/**
 * Created by alumno.desarrollo1 on 21/07/2016.
 */
public class Producto {

    private String nombre, precio, descripcion;

    public Producto(String nombre, String precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

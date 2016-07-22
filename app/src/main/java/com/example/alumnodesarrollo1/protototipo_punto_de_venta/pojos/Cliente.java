package com.example.alumnodesarrollo1.protototipo_punto_de_venta.pojos;

/**
 * Created by alumno.desarrollo1 on 22/07/2016.
 */
public class Cliente {
    private String nombre;
    private String rut;
    private String telefono;
    private String correo;
    private int cupo;

    public Cliente(String nombre, String rut, String telefono, String correo, int cupo) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.correo = correo;
        this.cupo = cupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }
}

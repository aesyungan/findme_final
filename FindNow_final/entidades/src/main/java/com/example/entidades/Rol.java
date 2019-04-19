package com.example.entidades;

/**
 * Created by XL on 10/12/2016.
 */

public class Rol  {
    private  int id_rol;
    private  String descripcion;
    private  int estado ;

    public Rol() {

    }

    public Rol(int id_rol, String descripcion, int estado) {
        this.id_rol = id_rol;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }

}

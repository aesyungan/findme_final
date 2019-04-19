package com.example.entidades;

/**
 * Created by XL on 10/12/2016.
 */

public class Usuario {

    private  int id_usuario;
    private  Rol rol;
    private  String nombres;
    private String apellidos ;
    private String cedula ;
    private String foto ;
    private String usuario;
    private String contrasena;
    private int estado;
    private int edad ;
    private String direccion ;

    public Usuario() {

    }

    public Usuario(int id_usuario, String direccion, int estado, int edad, String contrasena, String usuario, String foto, String cedula, String apellidos, String nombres, Rol rol) {
        this.id_usuario = id_usuario;
        this.direccion = direccion;
        this.estado = estado;
        this.edad = edad;
        this.contrasena = contrasena;
        this.usuario = usuario;
        this.foto = foto;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return this.id_usuario+" "+this.nombres +" "+this.apellidos +"->"+this.rol.getDescripcion();
    }

}

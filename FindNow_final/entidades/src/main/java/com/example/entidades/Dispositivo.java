package com.example.entidades;

/**
 * Created by XL on 3/1/2017.
 */
public class Dispositivo {

    private int id_dispositivo ;
    private String descripcion;
    private String imei ;
    private String fecha_update_registro ;
    private double latitud ;
    private double longitud ;
    private double altura ;
    private  String fechaupdate ;
    public Dispositivo() {
    }

    public Dispositivo(int id_dispositivo, String descripcion, String imei, String fecha_update_registro, double latitud, double longitud, double altura, String fechaupdate) {
        this.id_dispositivo = id_dispositivo;
        this.descripcion = descripcion;
        this.imei = imei;
        this.fecha_update_registro = fecha_update_registro;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altura = altura;
        this.fechaupdate = fechaupdate;
    }

    public String getFechaupdate() {
        return fechaupdate;
    }

    public void setFechaupdate(String fechaupdate) {
        this.fechaupdate = fechaupdate;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getFecha_update_registro() {
        return fecha_update_registro;
    }

    public void setFecha_update_registro(String fecha_update_registro) {
        this.fecha_update_registro = fecha_update_registro;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(int id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
}

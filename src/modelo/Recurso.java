/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jocelyn
 */

import java.util.Date;

public class Recurso {
    private int idRecurso;
    private int idProyecto;
    private String nombreArchivo;
    private String tipo;
    private String ubicacion;
    private Date fechaSubida;

    public Recurso() {
    }

    public Recurso(int idRecurso, int idProyecto, String nombreArchivo, String tipo, String ubicacion, Date fechaSubida) {
        this.idRecurso = idRecurso;
        this.idProyecto = idProyecto;
        this.nombreArchivo = nombreArchivo;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fechaSubida = fechaSubida;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}

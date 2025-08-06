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

public class LogErrores {
    private int idLog;
    private Date fecha;
    private String mensaje;
    private String clase;
    private String metodo;

    public LogErrores() {
    }

    public LogErrores(int idLog, Date fecha, String mensaje, String clase, String metodo) {
        this.idLog = idLog;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.clase = clase;
        this.metodo = metodo;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}

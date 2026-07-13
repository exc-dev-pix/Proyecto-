package com.restaurante;

import java.util.Date;

public class Incidente {

    private String descripcion;
    private double monto;
    private Date fecha;

    public Incidente(String descripcion, double monto, Date fecha) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return descripcion + " - S/ " + monto;
    }
}

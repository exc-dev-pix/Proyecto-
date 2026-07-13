package com.restaurante;

public class Producto implements Facturable {

    private String codigo;
    private String nombre;
    private double precioBase;
    private String categoria;

    public Producto(String codigo, String nombre, double precioBase, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.categoria = categoria;
    }

    @Override
    public double calcularSubtotal(int cantidad) {
        return precioBase * cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ") - S/ " + precioBase;
    }
}

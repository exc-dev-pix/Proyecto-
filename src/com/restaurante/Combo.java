package com.restaurante;

public class Combo implements Facturable {

    private String nombre;
    private double precioPromocional;
    private Producto[] productos;
    private int cantidadProductos;

    public Combo(String nombre, double precioPromocional) {
        this.nombre = nombre;
        this.precioPromocional = precioPromocional;
        this.productos = new Producto[4];
        this.cantidadProductos = 0;
    }

    public boolean agregarProducto(Producto producto) {
        if (cantidadProductos == 4) {
            System.out.println("No se pueden agregar mas de 4 productos a un combo.");
            return false;
        }
        productos[cantidadProductos] = producto;
        cantidadProductos++;
        return true;
    }

    public Producto[] getProductos() {
        Producto[] copia = new Producto[cantidadProductos];
        for (int i = 0; i < cantidadProductos; i++) {
            copia[i] = productos[i];
        }
        return copia;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioPromocional() {
        return precioPromocional;
    }

    public void setPrecioPromocional(double precioPromocional) {
        this.precioPromocional = precioPromocional;
    }

    @Override
    public double calcularSubtotal(int cantidad) {
        return precioPromocional * cantidad;
    }

    @Override
    public String toString() {
        return "Combo " + nombre + " - S/ " + precioPromocional + " (" + cantidadProductos + " productos)";
    }
}

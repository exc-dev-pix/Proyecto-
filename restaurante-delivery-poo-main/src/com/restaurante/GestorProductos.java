package com.restaurante;

public class GestorProductos {

    private Producto[] productos;
    private int cantidadProductos;

    public GestorProductos() {
        this.productos = new Producto[4];
        this.cantidadProductos = 0;
    }

    public void agregar(Producto producto) {
        if (cantidadProductos == productos.length) {
            Producto[] nuevoArreglo = new Producto[productos.length * 2];
            for (int i = 0; i < productos.length; i++) {
                nuevoArreglo[i] = productos[i];
            }
            productos = nuevoArreglo;
        }
        productos[cantidadProductos] = producto;
        cantidadProductos++;
    }

    public Producto buscarPorCodigo(String codigo) {
        for (int i = 0; i < cantidadProductos; i++) {
            if (productos[i].getCodigo().equals(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    public boolean eliminar(String codigo) {
        for (int i = 0; i < cantidadProductos; i++) {
            if (productos[i].getCodigo().equals(codigo)) {
                for (int j = i; j < cantidadProductos - 1; j++) {
                    productos[j] = productos[j + 1];
                }
                productos[cantidadProductos - 1] = null;
                cantidadProductos--;
                return true;
            }
        }
        return false;
    }

    public Producto[] listarTodos() {
        Producto[] copia = new Producto[cantidadProductos];
        for (int i = 0; i < cantidadProductos; i++) {
            copia[i] = productos[i];
        }
        return copia;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }
}

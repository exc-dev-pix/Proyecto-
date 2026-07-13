package com.restaurante;

public class GestorClientes {

    private Cliente[] clientes;
    private int cantidadClientes;

    public GestorClientes() {
        this.clientes = new Cliente[4];
        this.cantidadClientes = 0;
    }

    public void agregar(Cliente cliente) {
        if (cantidadClientes == clientes.length) {
            Cliente[] nuevoArreglo = new Cliente[clientes.length * 2];
            for (int i = 0; i < clientes.length; i++) {
                nuevoArreglo[i] = clientes[i];
            }
            clientes = nuevoArreglo;
        }
        clientes[cantidadClientes] = cliente;
        cantidadClientes++;
    }

    public Cliente buscarPorDniRuc(String dniRuc) {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getDniRuc().equals(dniRuc)) {
                return clientes[i];
            }
        }
        return null;
    }

    public boolean eliminar(String dniRuc) {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getDniRuc().equals(dniRuc)) {
                for (int j = i; j < cantidadClientes - 1; j++) {
                    clientes[j] = clientes[j + 1];
                }
                clientes[cantidadClientes - 1] = null;
                cantidadClientes--;
                return true;
            }
        }
        return false;
    }

    public Cliente[] listarTodos() {
        Cliente[] copia = new Cliente[cantidadClientes];
        for (int i = 0; i < cantidadClientes; i++) {
            copia[i] = clientes[i];
        }
        return copia;
    }

    public int getCantidadClientes() {
        return cantidadClientes;
    }
}

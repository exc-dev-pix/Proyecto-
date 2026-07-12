package com.restaurante;

public class GestorPedidos {

    private Pedido[] pedidos;
    private int cantidadPedidos;

    public GestorPedidos() {
        this.pedidos = new Pedido[4];
        this.cantidadPedidos = 0;
    }

    public Pedido crearPedido(Cliente cliente, String tipoEntrega, Cajero cajero) {
        Pedido pedido = new Pedido(cantidadPedidos + 1, cliente, tipoEntrega, cajero);
        agregar(pedido);
        return pedido;
    }

    private void agregar(Pedido pedido) {
        if (cantidadPedidos == pedidos.length) {
            Pedido[] nuevoArreglo = new Pedido[pedidos.length * 2];
            for (int i = 0; i < pedidos.length; i++) {
                nuevoArreglo[i] = pedidos[i];
            }
            pedidos = nuevoArreglo;
        }
        pedidos[cantidadPedidos] = pedido;
        cantidadPedidos++;
    }

    public Pedido buscarPorNumero(int numero) {
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getNumero() == numero) {
                return pedidos[i];
            }
        }
        return null;
    }

    public Pedido[] listarTodos() {
        Pedido[] copia = new Pedido[cantidadPedidos];
        for (int i = 0; i < cantidadPedidos; i++) {
            copia[i] = pedidos[i];
        }
        return copia;
    }

    public int getCantidadPedidos() {
        return cantidadPedidos;
    }
}

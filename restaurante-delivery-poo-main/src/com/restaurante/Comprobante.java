package com.restaurante;

public abstract class Comprobante {

    protected int numero;
    protected double subtotal;
    protected double igv;
    protected double total;
    protected Pedido pedido;

    public Comprobante(int numero, Pedido pedido) {
        this.numero = numero;
        this.pedido = pedido;
        this.subtotal = pedido.calcularTotal();
        this.igv = subtotal * 0.18;
        this.total = subtotal + igv;
    }

    public abstract void emitir();

    public int getNumero() {
        return numero;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public double getTotal() {
        return total;
    }

    public Pedido getPedido() {
        return pedido;
    }
}

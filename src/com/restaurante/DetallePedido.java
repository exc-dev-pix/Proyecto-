package com.restaurante;

public class DetallePedido implements Facturable {

    private Facturable item;
    private int cantidad;

    public DetallePedido(Facturable item, int cantidad) {
        this.item = item;
        this.cantidad = cantidad;
    }

    @Override
    public double calcularSubtotal(int cantidad) {
        return item.calcularSubtotal(cantidad);
    }

    public double calcularSubtotal() {
        return calcularSubtotal(cantidad);
    }

    public Facturable getItem() {
        return item;
    }

    public void setItem(Facturable item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return item + " x" + cantidad + " = S/ " + calcularSubtotal();
    }
}

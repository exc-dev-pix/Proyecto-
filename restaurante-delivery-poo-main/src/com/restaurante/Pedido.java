package com.restaurante;

import java.util.Date;

public class Pedido {

    private int numero;
    private String tipoEntrega;
    private String estado;
    private Date fecha;
    private Cliente cliente;
    private Cajero cajero;
    private Repartidor repartidor;
    private DetallePedido[] detalles;
    private int cantidadDetalles;
    private Incidente[] incidentes;
    private int cantidadIncidentes;

    public Pedido(int numero, Cliente cliente, String tipoEntrega, Cajero cajero) {
        this.numero = numero;
        this.cliente = cliente;
        this.cajero = cajero;
        this.tipoEntrega = tipoEntrega;
        this.estado = "En Cocina";
        this.fecha = new Date();
        this.detalles = new DetallePedido[4];
        this.cantidadDetalles = 0;
        this.incidentes = new Incidente[2];
        this.cantidadIncidentes = 0;
    }

    public void asignarRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public void agregarDetalle(DetallePedido detalle) {
        if (cantidadDetalles == detalles.length) {
            DetallePedido[] nuevoArreglo = new DetallePedido[detalles.length * 2];
            for (int i = 0; i < detalles.length; i++) {
                nuevoArreglo[i] = detalles[i];
            }
            detalles = nuevoArreglo;
        }
        detalles[cantidadDetalles] = detalle;
        cantidadDetalles++;
    }

    public void agregarIncidente(Incidente incidente) {
        if (cantidadIncidentes == incidentes.length) {
            Incidente[] nuevoArreglo = new Incidente[incidentes.length * 2];
            for (int i = 0; i < incidentes.length; i++) {
                nuevoArreglo[i] = incidentes[i];
            }
            incidentes = nuevoArreglo;
        }
        incidentes[cantidadIncidentes] = incidente;
        cantidadIncidentes++;
    }

    public void cambiarEstado(String nuevoEstado) {
        System.out.println("Pedido #" + numero + ": " + estado + " -> " + nuevoEstado);
        this.estado = nuevoEstado;
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < cantidadDetalles; i++) {
            total += detalles[i].calcularSubtotal();
        }
        for (int i = 0; i < cantidadIncidentes; i++) {
            total += incidentes[i].getMonto();
        }
        return total;
    }

    public DetallePedido[] getDetalles() {
        DetallePedido[] copia = new DetallePedido[cantidadDetalles];
        for (int i = 0; i < cantidadDetalles; i++) {
            copia[i] = detalles[i];
        }
        return copia;
    }

    public Incidente[] getIncidentes() {
        Incidente[] copia = new Incidente[cantidadIncidentes];
        for (int i = 0; i < cantidadIncidentes; i++) {
            copia[i] = incidentes[i];
        }
        return copia;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    @Override
    public String toString() {
        return "Pedido #" + numero + " - " + tipoEntrega + " - Estado: " + estado
                + " - Cliente: " + cliente.getNombres() + " " + cliente.getApellidos()
                + " - Total: S/ " + calcularTotal();
    }
}

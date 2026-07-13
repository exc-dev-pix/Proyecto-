package com.restaurante;

public class Boleta extends Comprobante {

    public Boleta(int numero, Pedido pedido) {
        super(numero, pedido);
    }

    @Override
    public void emitir() {
        Cliente cliente = pedido.getCliente();
        System.out.println("BOLETA DE VENTA B001-" + numero);
        System.out.println("Cliente: " + cliente.getNombres() + " " + cliente.getApellidos() + " - DNI: " + cliente.getDniRuc());
        System.out.println("Subtotal: S/ " + subtotal);
        System.out.println("IGV (18%): S/ " + igv);
        System.out.println("Total: S/ " + total);
    }
}

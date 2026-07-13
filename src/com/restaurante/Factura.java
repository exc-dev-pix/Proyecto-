package com.restaurante;

public class Factura extends Comprobante {

    public Factura(int numero, Pedido pedido) {
        super(numero, pedido);
    }

    @Override
    public void emitir() {
        Cliente cliente = pedido.getCliente();
        System.out.println("FACTURA F001-" + numero);
        System.out.println("Razon Social: " + cliente.getNombres() + " " + cliente.getApellidos() + " - RUC: " + cliente.getDniRuc());
        System.out.println("Subtotal: S/ " + subtotal);
        System.out.println("IGV (18%): S/ " + igv);
        System.out.println("Total: S/ " + total);
    }
}

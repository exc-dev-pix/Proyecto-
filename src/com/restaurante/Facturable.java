package com.restaurante;

/**
 * Contrato para todo lo que puede facturarse dentro de un pedido
 * (Producto, Combo, DetallePedido). Permite tratarlos de forma polimórfica
 * al calcular el subtotal de cada línea del pedido.
 */
public interface Facturable {

    double calcularSubtotal(int cantidad);
}

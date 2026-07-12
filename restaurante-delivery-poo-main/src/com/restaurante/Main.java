package com.restaurante;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("Sistema de Gestion - Restaurante Delivery POO");

        Vehiculos moto = new Vehiculos("ABC-123", "Moto");

        Cajero cajero = new Cajero("10000002", "Joaquin", "Perez", "jperez", "cajero123");
        Repartidor repartidor = new Repartidor("10000003", "Italo", "Gomez", "igomez", "repartidor123", moto);

        GestorEmpleados gestorEmpleados = new GestorEmpleados();
        gestorEmpleados.agregar(new Admin("10000001", "Diego", "Calderon", "dcalderon", "admin123"));
        gestorEmpleados.agregar(cajero);
        gestorEmpleados.agregar(repartidor);

        System.out.println("\nEmpleados registrados:");
        Empleado[] empleados = gestorEmpleados.listarTodos();
        for (int i = 0; i < empleados.length; i++) {
            System.out.println("- " + empleados[i]);
        }

        Empleado admin = gestorEmpleados.buscarPorDni("10000001");
        boolean acceso = admin.login("dcalderon", "admin123");
        System.out.println("\nLogin de " + admin.getNombres() + ": " + acceso);

        GestorClientes gestorClientes = new GestorClientes();
        gestorClientes.agregar(new Cliente("70000001", "Maria", "Lopez", "Av. Siempre Viva 123", "987654321"));
        Cliente cliente = gestorClientes.buscarPorDniRuc("70000001");
        System.out.println("\nCliente registrado: " + cliente);

        GestorProductos gestorProductos = new GestorProductos();
        gestorProductos.agregar(new Producto("P001", "Gaseosa 500ml", 5.0, "Bebida"));
        gestorProductos.agregar(new Producto("P002", "Hamburguesa Clasica", 12.0, "Comida"));

        System.out.println("\nProductos registrados:");
        Producto[] productos = gestorProductos.listarTodos();
        for (int i = 0; i < productos.length; i++) {
            System.out.println("- " + productos[i]);
        }

        Producto gaseosa = gestorProductos.buscarPorCodigo("P001");
        Producto hamburguesa = gestorProductos.buscarPorCodigo("P002");

        GestorCombos gestorCombos = new GestorCombos();
        Combo comboFamiliar = new Combo("Combo Familiar", 25.0);
        comboFamiliar.agregarProducto(hamburguesa);
        comboFamiliar.agregarProducto(gaseosa);
        gestorCombos.agregar(comboFamiliar);

        System.out.println("\nCombo registrado: " + gestorCombos.buscarPorNombre("Combo Familiar"));

        GestorPedidos gestorPedidos = new GestorPedidos();
        Pedido pedido = gestorPedidos.crearPedido(cliente, "Delivery", cajero);
        pedido.asignarRepartidor(repartidor);
        pedido.agregarDetalle(new DetallePedido(hamburguesa, 2));
        pedido.agregarDetalle(new DetallePedido(comboFamiliar, 1));
        pedido.agregarIncidente(new Incidente("Peaje por zona alejada", 3.5, pedido.getFecha()));

        System.out.println("\n" + pedido);
        pedido.cambiarEstado("En Camino");
        pedido.cambiarEstado("Entregado");
        System.out.println("Total final del pedido: S/ " + pedido.calcularTotal());

        Comprobante comprobante = new Boleta(1, pedido);
        System.out.println();
        comprobante.emitir();

        System.out.println("\nPedido #1 recuperado desde el gestor: " + gestorPedidos.buscarPorNumero(1));

        boolean eliminado = gestorProductos.eliminar("P001");
        System.out.println("\nSe elimino P001 (Gaseosa): " + eliminado);
        System.out.println("Productos restantes: " + gestorProductos.getCantidadProductos());

        GestorReportes gestorReportes = new GestorReportes(gestorPedidos);
        System.out.println();
        gestorReportes.reporteProductividad(gestorEmpleados);

        Date haceUnDia = new Date(pedido.getFecha().getTime() - 86400000L);
        Date manana = new Date(pedido.getFecha().getTime() + 86400000L);
        System.out.println();
        gestorReportes.reporteIngresos(haceUnDia, manana);
    }
}

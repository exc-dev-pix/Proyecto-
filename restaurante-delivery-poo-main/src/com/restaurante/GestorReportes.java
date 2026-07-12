package com.restaurante;

import java.util.Date;

public class GestorReportes {

    private GestorPedidos gestorPedidos;

    public GestorReportes(GestorPedidos gestorPedidos) {
        this.gestorPedidos = gestorPedidos;
    }

    public void reporteProductividad(GestorEmpleados gestorEmpleados) {
        Empleado[] empleados = gestorEmpleados.listarTodos();
        Pedido[] pedidos = gestorPedidos.listarTodos();

        System.out.println("=== Reporte de Productividad del Personal ===");
        for (int i = 0; i < empleados.length; i++) {
            Empleado empleado = empleados[i];
            if (empleado instanceof Cajero) {
                int pedidosAtendidos = 0;
                for (int j = 0; j < pedidos.length; j++) {
                    if (pedidos[j].getCajero() == empleado) {
                        pedidosAtendidos++;
                    }
                }
                System.out.println(empleado.getNombres() + " " + empleado.getApellidos()
                        + " (Cajero) - Pedidos atendidos: " + pedidosAtendidos);
            } else if (empleado instanceof Repartidor) {
                int entregasRealizadas = 0;
                for (int j = 0; j < pedidos.length; j++) {
                    if (pedidos[j].getRepartidor() == empleado && pedidos[j].getEstado().equals("Entregado")) {
                        entregasRealizadas++;
                    }
                }
                System.out.println(empleado.getNombres() + " " + empleado.getApellidos()
                        + " (Repartidor) - Entregas realizadas: " + entregasRealizadas);
            }
        }
    }

    public void reporteIngresos(Date desde, Date hasta) {
        Pedido[] pedidos = gestorPedidos.listarTodos();
        double totalMenuRegular = 0;
        double totalCombos = 0;

        for (int i = 0; i < pedidos.length; i++) {
            Date fecha = pedidos[i].getFecha();
            if (fecha.before(desde) || fecha.after(hasta)) {
                continue;
            }
            DetallePedido[] detalles = pedidos[i].getDetalles();
            for (int j = 0; j < detalles.length; j++) {
                if (detalles[j].getItem() instanceof Combo) {
                    totalCombos += detalles[j].calcularSubtotal();
                } else {
                    totalMenuRegular += detalles[j].calcularSubtotal();
                }
            }
        }

        System.out.println("=== Reporte de Ingresos (" + desde + " a " + hasta + ") ===");
        System.out.println("Menu regular: S/ " + totalMenuRegular);
        System.out.println("Combos promocionales: S/ " + totalCombos);
        System.out.println("Total: S/ " + (totalMenuRegular + totalCombos));
    }
}

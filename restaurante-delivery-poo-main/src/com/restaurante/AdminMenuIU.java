package com.restaurante;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AdminMenuIU extends JFrame {

    private Empleado empleado;
    private GestorEmpleados gestorEmpleados;
    private GestorProductos gestorProductos;
    private GestorClientes gestorClientes;
    private GestorCombos gestorCombos;
    private GestorPedidos gestorPedidos;
    private GestorReportes gestorReportes;

    public AdminMenuIU(Empleado empleado, GestorEmpleados gestorEmpleados, GestorProductos gestorProductos,
            GestorClientes gestorClientes, GestorCombos gestorCombos, GestorPedidos gestorPedidos,
            GestorReportes gestorReportes) {
        this.empleado = empleado;
        this.gestorEmpleados = gestorEmpleados;
        this.gestorProductos = gestorProductos;
        this.gestorClientes = gestorClientes;
        this.gestorCombos = gestorCombos;
        this.gestorPedidos = gestorPedidos;
        this.gestorReportes = gestorReportes;
        initComponents();
    }

    private void initComponents() {
        setTitle("Panel de Administrador");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(evt -> System.exit(0));
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        JLabel labelBienvenida = new JLabel("Bienvenido, " + empleado.getNombres() + " (Administrador)", SwingConstants.CENTER);
        add(labelBienvenida);
    }
}

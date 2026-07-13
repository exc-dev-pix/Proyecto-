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

        JMenu menuEmpleados = new JMenu("Empleados");
        JMenuItem itemNuevoEmpleado = new JMenuItem("Nuevo Empleado");
        itemNuevoEmpleado.addActionListener(evt -> new RegistroEmpleadoIU(gestorEmpleados).setVisible(true));
        menuEmpleados.add(itemNuevoEmpleado);
        JMenuItem itemListaEmpleados = new JMenuItem("Lista de Empleados");
        itemListaEmpleados.addActionListener(evt -> new ListaEmpleadosIU(gestorEmpleados).setVisible(true));
        menuEmpleados.add(itemListaEmpleados);
        menuBar.add(menuEmpleados);

        JMenu menuProductos = new JMenu("Productos");
        JMenuItem itemNuevoProducto = new JMenuItem("Nuevo Producto");
        itemNuevoProducto.addActionListener(evt -> new RegistroProductoIU(gestorProductos).setVisible(true));
        menuProductos.add(itemNuevoProducto);
        JMenuItem itemListaProductos = new JMenuItem("Lista de Productos");
        itemListaProductos.addActionListener(evt -> new ListaProductosIU(gestorProductos).setVisible(true));
        menuProductos.add(itemListaProductos);
        menuBar.add(menuProductos);

        JMenu menuCombos = new JMenu("Combos");
        JMenuItem itemNuevoCombo = new JMenuItem("Nuevo Combo");
        itemNuevoCombo.addActionListener(evt -> new RegistroComboIU(gestorCombos, gestorProductos).setVisible(true));
        menuCombos.add(itemNuevoCombo);
        JMenuItem itemListaCombos = new JMenuItem("Lista de Combos");
        itemListaCombos.addActionListener(evt -> new ListaCombosIU(gestorCombos).setVisible(true));
        menuCombos.add(itemListaCombos);
        menuBar.add(menuCombos);

        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem itemVerReportes = new JMenuItem("Ver Reportes");
        itemVerReportes.addActionListener(evt -> new ReportesIU(gestorReportes, gestorEmpleados).setVisible(true));
        menuReportes.add(itemVerReportes);
        menuBar.add(menuReportes);

        setJMenuBar(menuBar);

        JLabel labelBienvenida = new JLabel("Bienvenido, " + empleado.getNombres() + " (Administrador)", SwingConstants.CENTER);
        add(labelBienvenida);
    }
}

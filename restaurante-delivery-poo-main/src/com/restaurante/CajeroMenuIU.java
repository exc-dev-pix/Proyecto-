package com.restaurante;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class CajeroMenuIU extends JFrame {

    private Cajero cajero;
    private GestorClientes gestorClientes;
    private GestorProductos gestorProductos;
    private GestorCombos gestorCombos;
    private GestorPedidos gestorPedidos;
    private GestorEmpleados gestorEmpleados;

    public CajeroMenuIU(Cajero cajero, GestorClientes gestorClientes, GestorProductos gestorProductos,
            GestorCombos gestorCombos, GestorPedidos gestorPedidos, GestorEmpleados gestorEmpleados) {
        this.cajero = cajero;
        this.gestorClientes = gestorClientes;
        this.gestorProductos = gestorProductos;
        this.gestorCombos = gestorCombos;
        this.gestorPedidos = gestorPedidos;
        this.gestorEmpleados = gestorEmpleados;
        initComponents();
    }

    private void initComponents() {
        setTitle("Panel de Cajero");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(evt -> System.exit(0));
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);

        JMenu menuClientes = new JMenu("Clientes");
        JMenuItem itemNuevoCliente = new JMenuItem("Nuevo Cliente");
        itemNuevoCliente.addActionListener(evt -> new RegistroClienteIU(gestorClientes).setVisible(true));
        menuClientes.add(itemNuevoCliente);
        JMenuItem itemListaClientes = new JMenuItem("Lista de Clientes");
        itemListaClientes.addActionListener(evt -> new ListaClientesIU(gestorClientes).setVisible(true));
        menuClientes.add(itemListaClientes);
        menuBar.add(menuClientes);

        JMenu menuPedidos = new JMenu("Pedidos");
        JMenuItem itemNuevoPedido = new JMenuItem("Nuevo Pedido");
        itemNuevoPedido.addActionListener(evt -> new RegistroPedidoIU(
                cajero, gestorClientes, gestorProductos, gestorCombos, gestorPedidos, gestorEmpleados
        ).setVisible(true));
        menuPedidos.add(itemNuevoPedido);
        JMenuItem itemListaPedidos = new JMenuItem("Lista de Pedidos");
        itemListaPedidos.addActionListener(evt -> new ListaPedidosIU(gestorPedidos).setVisible(true));
        menuPedidos.add(itemListaPedidos);
        menuBar.add(menuPedidos);

        setJMenuBar(menuBar);

        JLabel labelBienvenida = new JLabel("Bienvenido, " + cajero.getNombres() + " (Cajero)", SwingConstants.CENTER);
        add(labelBienvenida);
    }
}

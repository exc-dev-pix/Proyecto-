package com.restaurante;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class RepartidorMenuIU extends JFrame {

    private Repartidor repartidor;
    private GestorPedidos gestorPedidos;

    public RepartidorMenuIU(Repartidor repartidor, GestorPedidos gestorPedidos) {
        this.repartidor = repartidor;
        this.gestorPedidos = gestorPedidos;
        initComponents();
    }

    private void initComponents() {
        setTitle("Panel de Repartidor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(evt -> System.exit(0));
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);

        JMenu menuPedidos = new JMenu("Pedidos");
        JMenuItem itemMisEntregas = new JMenuItem("Mis Entregas");
        itemMisEntregas.addActionListener(evt -> new ListaEntregasIU(repartidor, gestorPedidos).setVisible(true));
        menuPedidos.add(itemMisEntregas);
        menuBar.add(menuPedidos);

        setJMenuBar(menuBar);

        JLabel labelBienvenida = new JLabel("Bienvenido, " + repartidor.getNombres() + " (Repartidor)", SwingConstants.CENTER);
        add(labelBienvenida);
    }
}

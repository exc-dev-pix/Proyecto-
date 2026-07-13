package com.restaurante;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class ListaEntregasIU extends JFrame {

    private Repartidor repartidor;
    private GestorPedidos gestorPedidos;
    private DefaultTableModel modeloTabla;
    private JTable tablaEntregas;

    public ListaEntregasIU(Repartidor repartidor, GestorPedidos gestorPedidos) {
        this.repartidor = repartidor;
        this.gestorPedidos = gestorPedidos;
        initComponents();
        cargarEntregas();
    }

    private void initComponents() {
        setTitle("Mis Entregas");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel(new String[]{"Numero", "Cliente", "Estado", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaEntregas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEntregas);

        JButton btnMarcarEntregado = new JButton("Marcar Entregado");
        btnMarcarEntregado.addActionListener(evt -> marcarEntregado());

        JButton btnReportarIncidente = new JButton("Reportar Incidente");
        btnReportarIncidente.addActionListener(evt -> reportarIncidente());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(evt -> dispose());

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnMarcarEntregado);
        panelBotones.add(btnReportarIncidente);
        panelBotones.add(btnCerrar);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarEntregas() {
        modeloTabla.setRowCount(0);
        Pedido[] pedidos = gestorPedidos.listarTodos();
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i].getRepartidor() == repartidor) {
                modeloTabla.addRow(new Object[]{
                    pedidos[i].getNumero(),
                    pedidos[i].getCliente().getNombres() + " " + pedidos[i].getCliente().getApellidos(),
                    pedidos[i].getEstado(),
                    "S/ " + pedidos[i].calcularTotal()
                });
            }
        }
    }

    private Pedido obtenerPedidoSeleccionado() {
        int fila = tablaEntregas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        int numero = (int) modeloTabla.getValueAt(fila, 0);
        return gestorPedidos.buscarPorNumero(numero);
    }

    private void marcarEntregado() {
        Pedido pedido = obtenerPedidoSeleccionado();
        if (pedido == null) {
            return;
        }
        if (pedido.getEstado().equals("Entregado")) {
            JOptionPane.showMessageDialog(this, "Este pedido ya esta entregado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        pedido.cambiarEstado("Entregado");
        repartidor.setDisponible(true);
        JOptionPane.showMessageDialog(this, "Pedido #" + pedido.getNumero() + " marcado como Entregado");
        cargarEntregas();
    }

    private void reportarIncidente() {
        Pedido pedido = obtenerPedidoSeleccionado();
        if (pedido == null) {
            return;
        }
        String descripcion = JOptionPane.showInputDialog(this, "Descripcion del incidente:");
        if (descripcion == null || descripcion.isEmpty()) {
            return;
        }
        String montoTexto = JOptionPane.showInputDialog(this, "Monto del recargo (S/):");
        if (montoTexto == null || montoTexto.isEmpty()) {
            return;
        }
        double monto;
        try {
            monto = Double.parseDouble(montoTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pedido.agregarIncidente(new Incidente(descripcion, monto, pedido.getFecha()));
        JOptionPane.showMessageDialog(this, "Incidente registrado en el pedido #" + pedido.getNumero());
        cargarEntregas();
    }
}

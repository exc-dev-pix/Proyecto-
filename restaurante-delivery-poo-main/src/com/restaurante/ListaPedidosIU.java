package com.restaurante;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class ListaPedidosIU extends JFrame {

    private GestorPedidos gestorPedidos;
    private DefaultTableModel modeloTabla;
    private JTable tablaPedidos;

    public ListaPedidosIU(GestorPedidos gestorPedidos) {
        this.gestorPedidos = gestorPedidos;
        initComponents();
        cargarPedidos();
    }

    private void initComponents() {
        setTitle("Lista de Pedidos");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel(new String[]{"Numero", "Cliente", "Tipo Entrega", "Estado", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaPedidos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        JButton btnMarcarEntregado = new JButton("Marcar Entregado");
        btnMarcarEntregado.addActionListener(evt -> marcarEntregado());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(evt -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnMarcarEntregado);
        panelBotones.add(btnCerrar);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarPedidos() {
        modeloTabla.setRowCount(0);
        Pedido[] pedidos = gestorPedidos.listarTodos();
        for (int i = 0; i < pedidos.length; i++) {
            modeloTabla.addRow(new Object[]{
                pedidos[i].getNumero(),
                pedidos[i].getCliente().getNombres() + " " + pedidos[i].getCliente().getApellidos(),
                pedidos[i].getTipoEntrega(),
                pedidos[i].getEstado(),
                "S/ " + pedidos[i].calcularTotal()
            });
        }
    }

    private void marcarEntregado() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int numero = (int) modeloTabla.getValueAt(fila, 0);
        Pedido pedido = gestorPedidos.buscarPorNumero(numero);
        if (pedido.getEstado().equals("Entregado")) {
            JOptionPane.showMessageDialog(this, "Este pedido ya esta entregado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        pedido.cambiarEstado("Entregado");
        if (pedido.getRepartidor() != null) {
            pedido.getRepartidor().setDisponible(true);
        }
        cargarPedidos();
    }
}

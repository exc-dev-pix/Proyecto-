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

public class ListaClientesIU extends JFrame {

    private GestorClientes gestorClientes;
    private DefaultTableModel modeloTabla;
    private JTable tablaClientes;

    public ListaClientesIU(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
        initComponents();
        cargarClientes();
    }

    private void initComponents() {
        setTitle("Lista de Clientes");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel(new String[]{"DNI/RUC", "Nombres", "Apellidos", "Direccion", "Telefono"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(evt -> eliminar());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(evt -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarClientes() {
        modeloTabla.setRowCount(0);
        Cliente[] clientes = gestorClientes.listarTodos();
        for (int i = 0; i < clientes.length; i++) {
            modeloTabla.addRow(new Object[]{
                clientes[i].getDniRuc(),
                clientes[i].getNombres(),
                clientes[i].getApellidos(),
                clientes[i].getDireccion(),
                clientes[i].getTelefono()
            });
        }
    }

    private void eliminar() {
        int fila = tablaClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String dniRuc = (String) modeloTabla.getValueAt(fila, 0);
        gestorClientes.eliminar(dniRuc);
        cargarClientes();
    }
}

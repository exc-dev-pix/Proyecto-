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

public class ListaProductosIU extends JFrame {

    private GestorProductos gestorProductos;
    private DefaultTableModel modeloTabla;
    private JTable tablaProductos;

    public ListaProductosIU(GestorProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
        initComponents();
        cargarProductos();
    }

    private void initComponents() {
        setTitle("Lista de Productos");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel(new String[]{"Codigo", "Nombre", "Precio Base", "Categoria"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

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

    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        Producto[] productos = gestorProductos.listarTodos();
        for (int i = 0; i < productos.length; i++) {
            modeloTabla.addRow(new Object[]{
                productos[i].getCodigo(),
                productos[i].getNombre(),
                "S/ " + productos[i].getPrecioBase(),
                productos[i].getCategoria()
            });
        }
    }

    private void eliminar() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String codigo = (String) modeloTabla.getValueAt(fila, 0);
        gestorProductos.eliminar(codigo);
        cargarProductos();
    }
}

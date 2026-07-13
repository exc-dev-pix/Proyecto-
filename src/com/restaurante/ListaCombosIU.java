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

public class ListaCombosIU extends JFrame {

    private GestorCombos gestorCombos;
    private DefaultTableModel modeloTabla;
    private JTable tablaCombos;

    public ListaCombosIU(GestorCombos gestorCombos) {
        this.gestorCombos = gestorCombos;
        initComponents();
        cargarCombos();
    }

    private void initComponents() {
        setTitle("Lista de Combos");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Precio Promocional", "Cantidad de Productos"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaCombos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaCombos);

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

    private void cargarCombos() {
        modeloTabla.setRowCount(0);
        Combo[] combos = gestorCombos.listarTodos();
        for (int i = 0; i < combos.length; i++) {
            modeloTabla.addRow(new Object[]{
                combos[i].getNombre(),
                "S/ " + combos[i].getPrecioPromocional(),
                combos[i].getCantidadProductos()
            });
        }
    }

    private void eliminar() {
        int fila = tablaCombos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un combo de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nombre = (String) modeloTabla.getValueAt(fila, 0);
        gestorCombos.eliminar(nombre);
        cargarCombos();
    }
}

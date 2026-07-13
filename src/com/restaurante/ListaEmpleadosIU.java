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

public class ListaEmpleadosIU extends JFrame {

    private GestorEmpleados gestorEmpleados;
    private DefaultTableModel modeloTabla;
    private JTable tablaEmpleados;

    public ListaEmpleadosIU(GestorEmpleados gestorEmpleados) {
        this.gestorEmpleados = gestorEmpleados;
        initComponents();
        cargarEmpleados();
    }

    private void initComponents() {
        setTitle("Lista de Empleados");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel(new String[]{"DNI", "Nombres", "Apellidos", "Login", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaEmpleados = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);

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

    private void cargarEmpleados() {
        modeloTabla.setRowCount(0);
        Empleado[] empleados = gestorEmpleados.listarTodos();
        for (int i = 0; i < empleados.length; i++) {
            modeloTabla.addRow(new Object[]{
                empleados[i].getDni(),
                empleados[i].getNombres(),
                empleados[i].getApellidos(),
                empleados[i].getLogin(),
                empleados[i].getRol()
            });
        }
    }

    private void eliminar() {
        int fila = tablaEmpleados.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un empleado de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String dni = (String) modeloTabla.getValueAt(fila, 0);
        gestorEmpleados.eliminar(dni);
        cargarEmpleados();
    }
}

package com.restaurante;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class RegistroClienteIU extends JFrame {

    private GestorClientes gestorClientes;

    private JTextField txtDniRuc;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDireccion;
    private JTextField txtTelefono;

    public RegistroClienteIU(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
        initComponents();
    }

    private void initComponents() {
        setTitle("Registro de un Nuevo Cliente");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        panelFormulario.add(new JLabel("DNI/RUC:"));
        txtDniRuc = new JTextField();
        panelFormulario.add(txtDniRuc);
        panelFormulario.add(new JLabel("Nombres:"));
        txtNombres = new JTextField();
        panelFormulario.add(txtNombres);
        panelFormulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelFormulario.add(txtApellidos);
        panelFormulario.add(new JLabel("Direccion:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);
        panelFormulario.add(new JLabel("Telefono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        JButton btnGrabar = new JButton("Grabar");
        btnGrabar.addActionListener(evt -> grabar());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(evt -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGrabar);
        panelBotones.add(btnCerrar);

        setLayout(new java.awt.BorderLayout());
        add(panelFormulario, java.awt.BorderLayout.CENTER);
        add(panelBotones, java.awt.BorderLayout.SOUTH);
    }

    private void grabar() {
        if (txtDniRuc.getText().isEmpty() || txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DNI/RUC, Nombres y Apellidos son obligatorios", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Cliente cliente = new Cliente(txtDniRuc.getText(), txtNombres.getText(), txtApellidos.getText(),
                txtDireccion.getText(), txtTelefono.getText());
        gestorClientes.agregar(cliente);
        JOptionPane.showMessageDialog(this, "Cliente grabado correctamente");

        txtDniRuc.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
}

package com.restaurante;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class RegistroEmpleadoIU extends JFrame {

    private GestorEmpleados gestorEmpleados;

    private JTextField txtDni;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private JComboBox cboRol;
    private JTextField txtPlacaVehiculo;
    private JTextField txtTipoVehiculo;

    public RegistroEmpleadoIU(GestorEmpleados gestorEmpleados) {
        this.gestorEmpleados = gestorEmpleados;
        initComponents();
    }

    private void initComponents() {
        setTitle("Registro de un Nuevo Empleado");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 380);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 5, 5));
        panelFormulario.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        panelFormulario.add(txtDni);
        panelFormulario.add(new JLabel("Nombres:"));
        txtNombres = new JTextField();
        panelFormulario.add(txtNombres);
        panelFormulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelFormulario.add(txtApellidos);
        panelFormulario.add(new JLabel("Login:"));
        txtLogin = new JTextField();
        panelFormulario.add(txtLogin);
        panelFormulario.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panelFormulario.add(txtPassword);

        panelFormulario.add(new JLabel("Rol:"));
        cboRol = new JComboBox();
        cboRol.addItem("Administrador");
        cboRol.addItem("Cajero");
        cboRol.addItem("Repartidor");
        cboRol.addActionListener(evt -> habilitarVehiculo("Repartidor".equals(cboRol.getSelectedItem())));
        panelFormulario.add(cboRol);

        panelFormulario.add(new JLabel("Placa Vehiculo:"));
        txtPlacaVehiculo = new JTextField();
        panelFormulario.add(txtPlacaVehiculo);
        panelFormulario.add(new JLabel("Tipo Vehiculo:"));
        txtTipoVehiculo = new JTextField();
        panelFormulario.add(txtTipoVehiculo);

        habilitarVehiculo(false);

        JButton btnGrabar = new JButton("Grabar");
        btnGrabar.addActionListener(evt -> grabar());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(evt -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGrabar);
        panelBotones.add(btnCerrar);

        setLayout(new BorderLayout());
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void habilitarVehiculo(boolean habilitado) {
        txtPlacaVehiculo.setEnabled(habilitado);
        txtTipoVehiculo.setEnabled(habilitado);
    }

    private void grabar() {
        if (txtDni.getText().isEmpty() || txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty()
                || txtLogin.getText().isEmpty() || txtPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "DNI, Nombres, Apellidos, Login y Password son obligatorios", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dni = txtDni.getText();
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String login = txtLogin.getText();
        String password = new String(txtPassword.getPassword());
        String rol = (String) cboRol.getSelectedItem();

        Empleado empleado;
        if (rol.equals("Administrador")) {
            empleado = new Admin(dni, nombres, apellidos, login, password);
        } else if (rol.equals("Cajero")) {
            empleado = new Cajero(dni, nombres, apellidos, login, password);
        } else {
            if (txtPlacaVehiculo.getText().isEmpty() || txtTipoVehiculo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Placa y Tipo de Vehiculo son obligatorios para un Repartidor", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Vehiculos vehiculo = new Vehiculos(txtPlacaVehiculo.getText(), txtTipoVehiculo.getText());
            empleado = new Repartidor(dni, nombres, apellidos, login, password, vehiculo);
        }

        gestorEmpleados.agregar(empleado);
        JOptionPane.showMessageDialog(this, "Empleado grabado correctamente");

        txtDni.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtLogin.setText("");
        txtPassword.setText("");
        cboRol.setSelectedIndex(0);
        txtPlacaVehiculo.setText("");
        txtTipoVehiculo.setText("");
    }
}

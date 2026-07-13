package com.restaurante;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginIU extends JFrame {

    private GestorEmpleados gestorEmpleados;
    private GestorProductos gestorProductos;
    private GestorClientes gestorClientes;
    private GestorCombos gestorCombos;
    private GestorPedidos gestorPedidos;
    private GestorReportes gestorReportes;

    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public LoginIU() {
        inicializarGestores();
        initComponents();
    }

    private void inicializarGestores() {
        Vehiculos moto = new Vehiculos("ABC-123", "Moto");

        Cajero cajero = new Cajero("10000002", "Joaquin", "Perez", "jperez", "cajero123");
        Repartidor repartidor = new Repartidor("10000003", "Italo", "Gomez", "igomez", "repartidor123", moto);

        gestorEmpleados = new GestorEmpleados();
        gestorEmpleados.agregar(new Admin("10000001", "Diego", "Calderon", "dcalderon", "admin123"));
        gestorEmpleados.agregar(cajero);
        gestorEmpleados.agregar(repartidor);

        gestorClientes = new GestorClientes();
        Cliente cliente = new Cliente("70000001", "Maria", "Lopez", "Av. Siempre Viva 123", "987654321");
        gestorClientes.agregar(cliente);

        gestorProductos = new GestorProductos();
        Producto gaseosa = new Producto("P001", "Gaseosa 500ml", 5.0, "Bebida");
        Producto hamburguesa = new Producto("P002", "Hamburguesa Clasica", 12.0, "Comida");
        gestorProductos.agregar(gaseosa);
        gestorProductos.agregar(hamburguesa);

        gestorCombos = new GestorCombos();

        gestorPedidos = new GestorPedidos();
        Pedido pedidoDemo = gestorPedidos.crearPedido(cliente, "Delivery", cajero);
        pedidoDemo.asignarRepartidor(repartidor);
        pedidoDemo.agregarDetalle(new DetallePedido(hamburguesa, 2));
        pedidoDemo.agregarDetalle(new DetallePedido(gaseosa, 1));

        gestorReportes = new GestorReportes(gestorPedidos);
    }

    private void initComponents() {
        setTitle("Sistema de Gestion - Restaurante Delivery POO - Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 180);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        panelFormulario.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelFormulario.add(txtUsuario);
        panelFormulario.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panelFormulario.add(txtPassword);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(evt -> ingresar());

        setLayout(new BorderLayout(10, 10));
        add(panelFormulario, BorderLayout.CENTER);
        add(btnIngresar, BorderLayout.SOUTH);
    }

    private void ingresar() {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        Empleado empleado = gestorEmpleados.autenticar(usuario, password);
        if (empleado == null) {
            JOptionPane.showMessageDialog(this, "Usuario o password incorrectos", "Error de acceso", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (empleado instanceof Admin) {
            new AdminMenuIU(empleado, gestorEmpleados, gestorProductos, gestorClientes, gestorCombos, gestorPedidos, gestorReportes).setVisible(true);
        } else if (empleado instanceof Cajero) {
            new CajeroMenuIU((Cajero) empleado, gestorClientes, gestorProductos, gestorCombos, gestorPedidos, gestorEmpleados).setVisible(true);
        } else if (empleado instanceof Repartidor) {
            new RepartidorMenuIU((Repartidor) empleado, gestorPedidos).setVisible(true);
        }

        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new LoginIU().setVisible(true));
    }
}

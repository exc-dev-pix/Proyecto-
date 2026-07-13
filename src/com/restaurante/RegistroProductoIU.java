package com.restaurante;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class RegistroProductoIU extends JFrame {

    private GestorProductos gestorProductos;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecioBase;
    private JTextField txtCategoria;

    public RegistroProductoIU(GestorProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
        initComponents();
    }

    private void initComponents() {
        setTitle("Registro de un Nuevo Producto");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 240);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        panelFormulario.add(new JLabel("Codigo:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Precio Base:"));
        txtPrecioBase = new JTextField();
        panelFormulario.add(txtPrecioBase);
        panelFormulario.add(new JLabel("Categoria:"));
        txtCategoria = new JTextField();
        panelFormulario.add(txtCategoria);

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

    private void grabar() {
        if (txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty() || txtCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Codigo, Nombre y Categoria son obligatorios", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double precioBase;
        try {
            precioBase = Double.parseDouble(txtPrecioBase.getText());
            if (precioBase <= 0) {
                JOptionPane.showMessageDialog(this, "El precio base debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio base debe ser un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Producto producto = new Producto(txtCodigo.getText(), txtNombre.getText(), precioBase, txtCategoria.getText());
        gestorProductos.agregar(producto);
        JOptionPane.showMessageDialog(this, "Producto grabado correctamente");

        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecioBase.setText("");
        txtCategoria.setText("");
    }
}

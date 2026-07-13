package com.restaurante;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class RegistroComboIU extends JFrame {

    private GestorCombos gestorCombos;
    private GestorProductos gestorProductos;

    private Producto[] productosDisponibles;
    private Combo comboActual;

    private JTextField txtNombre;
    private JTextField txtPrecioPromocional;
    private JButton btnIniciarCombo;
    private JComboBox cboProducto;
    private JButton btnAgregarProducto;
    private DefaultTableModel modeloTabla;
    private JTable tablaProductos;
    private JButton btnFinalizar;

    public RegistroComboIU(GestorCombos gestorCombos, GestorProductos gestorProductos) {
        this.gestorCombos = gestorCombos;
        this.gestorProductos = gestorProductos;
        initComponents();
    }

    private void initComponents() {
        setTitle("Registro de un Nuevo Combo");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 420);
        setLocationRelativeTo(null);

        JPanel panelInicio = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInicio.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelInicio.add(txtNombre);
        panelInicio.add(new JLabel("Precio Promocional:"));
        txtPrecioPromocional = new JTextField();
        panelInicio.add(txtPrecioPromocional);
        btnIniciarCombo = new JButton("Crear Combo");
        btnIniciarCombo.addActionListener(evt -> iniciarCombo());
        panelInicio.add(new JLabel());
        panelInicio.add(btnIniciarCombo);

        JPanel panelProducto = new JPanel(new GridLayout(1, 3, 5, 5));
        cboProducto = new JComboBox();
        productosDisponibles = gestorProductos.listarTodos();
        for (int i = 0; i < productosDisponibles.length; i++) {
            cboProducto.addItem(productosDisponibles[i].getCodigo() + " - " + productosDisponibles[i].getNombre());
        }
        panelProducto.add(cboProducto);
        btnAgregarProducto = new JButton("Agregar Producto al Combo");
        btnAgregarProducto.addActionListener(evt -> agregarProducto());
        panelProducto.add(btnAgregarProducto);
        panelProducto.add(new JLabel());

        habilitarSeccionProductos(false);

        modeloTabla = new DefaultTableModel(new String[]{"Producto", "Precio Base"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        btnFinalizar = new JButton("Finalizar Combo");
        btnFinalizar.addActionListener(evt -> finalizarCombo());
        btnFinalizar.setEnabled(false);
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnFinalizar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelInicio, BorderLayout.NORTH);
        panelSuperior.add(panelProducto, BorderLayout.SOUTH);

        setLayout(new BorderLayout(5, 5));
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void habilitarSeccionProductos(boolean habilitado) {
        cboProducto.setEnabled(habilitado);
        btnAgregarProducto.setEnabled(habilitado);
    }

    private void iniciarCombo() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del combo es obligatorio", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double precioPromocional;
        try {
            precioPromocional = Double.parseDouble(txtPrecioPromocional.getText());
            if (precioPromocional <= 0) {
                JOptionPane.showMessageDialog(this, "El precio promocional debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio promocional debe ser un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (productosDisponibles.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay productos registrados para armar un combo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        comboActual = new Combo(txtNombre.getText(), precioPromocional);
        txtNombre.setEnabled(false);
        txtPrecioPromocional.setEnabled(false);
        btnIniciarCombo.setEnabled(false);
        habilitarSeccionProductos(true);
        btnFinalizar.setEnabled(true);
    }

    private void agregarProducto() {
        Producto producto = productosDisponibles[cboProducto.getSelectedIndex()];
        boolean agregado = comboActual.agregarProducto(producto);
        if (!agregado) {
            JOptionPane.showMessageDialog(this, "El combo ya tiene el maximo de 4 productos", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        modeloTabla.addRow(new Object[]{producto.getNombre(), "S/ " + producto.getPrecioBase()});
        if (comboActual.getCantidadProductos() == 4) {
            habilitarSeccionProductos(false);
        }
    }

    private void finalizarCombo() {
        if (comboActual.getCantidadProductos() == 0) {
            JOptionPane.showMessageDialog(this, "Agrega al menos un producto al combo", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gestorCombos.agregar(comboActual);
        JOptionPane.showMessageDialog(this, "Combo grabado correctamente");
        dispose();
    }
}

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

public class RegistroPedidoIU extends JFrame {

    private Cajero cajero;
    private GestorClientes gestorClientes;
    private GestorProductos gestorProductos;
    private GestorCombos gestorCombos;
    private GestorPedidos gestorPedidos;
    private GestorEmpleados gestorEmpleados;

    private Cliente[] clientesDisponibles;
    private Producto[] productosDisponibles;
    private Combo[] combosDisponibles;
    private Pedido pedidoActual;

    private JComboBox cboCliente;
    private JComboBox cboTipoEntrega;
    private JButton btnIniciarPedido;
    private JComboBox cboProducto;
    private JTextField txtCantidadProducto;
    private JButton btnAgregarProducto;
    private JComboBox cboCombo;
    private JTextField txtCantidadCombo;
    private JButton btnAgregarCombo;
    private DefaultTableModel modeloTabla;
    private JTable tablaItems;
    private JLabel labelTotal;
    private JButton btnFinalizar;

    public RegistroPedidoIU(Cajero cajero, GestorClientes gestorClientes, GestorProductos gestorProductos,
            GestorCombos gestorCombos, GestorPedidos gestorPedidos, GestorEmpleados gestorEmpleados) {
        this.cajero = cajero;
        this.gestorClientes = gestorClientes;
        this.gestorProductos = gestorProductos;
        this.gestorCombos = gestorCombos;
        this.gestorPedidos = gestorPedidos;
        this.gestorEmpleados = gestorEmpleados;
        initComponents();
    }

    private void initComponents() {
        setTitle("Registro de un Nuevo Pedido");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        JPanel panelInicio = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInicio.add(new JLabel("Cliente:"));
        cboCliente = new JComboBox();
        clientesDisponibles = gestorClientes.listarTodos();
        for (int i = 0; i < clientesDisponibles.length; i++) {
            cboCliente.addItem(clientesDisponibles[i].getDniRuc() + " - " + clientesDisponibles[i].getNombres() + " " + clientesDisponibles[i].getApellidos());
        }
        panelInicio.add(cboCliente);

        panelInicio.add(new JLabel("Tipo de Entrega:"));
        cboTipoEntrega = new JComboBox();
        cboTipoEntrega.addItem("Delivery");
        cboTipoEntrega.addItem("Recojo en Tienda");
        panelInicio.add(cboTipoEntrega);

        btnIniciarPedido = new JButton("Iniciar Pedido");
        btnIniciarPedido.addActionListener(evt -> iniciarPedido());
        panelInicio.add(new JLabel());
        panelInicio.add(btnIniciarPedido);

        JPanel panelItems = new JPanel(new GridLayout(2, 4, 5, 5));
        panelItems.add(new JLabel("Producto:"));
        cboProducto = new JComboBox();
        productosDisponibles = gestorProductos.listarTodos();
        for (int i = 0; i < productosDisponibles.length; i++) {
            cboProducto.addItem(productosDisponibles[i].getCodigo() + " - " + productosDisponibles[i].getNombre() + " - S/ " + productosDisponibles[i].getPrecioBase());
        }
        panelItems.add(cboProducto);
        txtCantidadProducto = new JTextField();
        panelItems.add(txtCantidadProducto);
        btnAgregarProducto = new JButton("Agregar Producto");
        btnAgregarProducto.addActionListener(evt -> agregarProducto());
        panelItems.add(btnAgregarProducto);

        panelItems.add(new JLabel("Combo:"));
        cboCombo = new JComboBox();
        combosDisponibles = gestorCombos.listarTodos();
        for (int i = 0; i < combosDisponibles.length; i++) {
            cboCombo.addItem(combosDisponibles[i].getNombre() + " - S/ " + combosDisponibles[i].getPrecioPromocional());
        }
        panelItems.add(cboCombo);
        txtCantidadCombo = new JTextField();
        panelItems.add(txtCantidadCombo);
        btnAgregarCombo = new JButton("Agregar Combo");
        btnAgregarCombo.addActionListener(evt -> agregarCombo());
        panelItems.add(btnAgregarCombo);

        habilitarSeccionItems(false);

        modeloTabla = new DefaultTableModel(new String[]{"Item", "Cantidad", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaItems = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaItems);

        labelTotal = new JLabel("Total: S/ 0.0");
        btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.addActionListener(evt -> finalizarPedido());
        btnFinalizar.setEnabled(false);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(labelTotal, BorderLayout.WEST);
        panelInferior.add(btnFinalizar, BorderLayout.EAST);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelInicio, BorderLayout.NORTH);
        panelSuperior.add(panelItems, BorderLayout.SOUTH);

        setLayout(new BorderLayout(5, 5));
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void habilitarSeccionItems(boolean habilitado) {
        cboProducto.setEnabled(habilitado);
        txtCantidadProducto.setEnabled(habilitado);
        btnAgregarProducto.setEnabled(habilitado);
        cboCombo.setEnabled(habilitado);
        txtCantidadCombo.setEnabled(habilitado);
        btnAgregarCombo.setEnabled(habilitado);
    }

    private void iniciarPedido() {
        if (clientesDisponibles.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay clientes registrados", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Cliente cliente = clientesDisponibles[cboCliente.getSelectedIndex()];
        String tipoEntrega = (String) cboTipoEntrega.getSelectedItem();

        pedidoActual = gestorPedidos.crearPedido(cliente, tipoEntrega, cajero);

        if (tipoEntrega.equals("Delivery")) {
            Repartidor repartidor = buscarRepartidorDisponible();
            if (repartidor != null) {
                pedidoActual.asignarRepartidor(repartidor);
                repartidor.setDisponible(false);
            } else {
                JOptionPane.showMessageDialog(this, "No hay repartidores disponibles en este momento", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(this, "Pedido #" + pedidoActual.getNumero() + " iniciado");
        cboCliente.setEnabled(false);
        cboTipoEntrega.setEnabled(false);
        btnIniciarPedido.setEnabled(false);
        habilitarSeccionItems(true);
        btnFinalizar.setEnabled(true);
    }

    private Repartidor buscarRepartidorDisponible() {
        Empleado[] empleados = gestorEmpleados.listarTodos();
        for (int i = 0; i < empleados.length; i++) {
            if (empleados[i] instanceof Repartidor) {
                Repartidor repartidor = (Repartidor) empleados[i];
                if (repartidor.isDisponible() && repartidor.getVehiculo().isOperativo()) {
                    return repartidor;
                }
            }
        }
        return null;
    }

    private void agregarProducto() {
        if (productosDisponibles.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay productos registrados", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int cantidad = leerCantidad(txtCantidadProducto);
        if (cantidad <= 0) {
            return;
        }
        Producto producto = productosDisponibles[cboProducto.getSelectedIndex()];
        pedidoActual.agregarDetalle(new DetallePedido(producto, cantidad));
        txtCantidadProducto.setText("");
        actualizarTabla();
    }

    private void agregarCombo() {
        if (combosDisponibles.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay combos registrados", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int cantidad = leerCantidad(txtCantidadCombo);
        if (cantidad <= 0) {
            return;
        }
        Combo combo = combosDisponibles[cboCombo.getSelectedIndex()];
        pedidoActual.agregarDetalle(new DetallePedido(combo, cantidad));
        txtCantidadCombo.setText("");
        actualizarTabla();
    }

    private int leerCantidad(JTextField campo) {
        try {
            int cantidad = Integer.parseInt(campo.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            return cantidad;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un numero entero", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        DetallePedido[] detalles = pedidoActual.getDetalles();
        for (int i = 0; i < detalles.length; i++) {
            modeloTabla.addRow(new Object[]{
                detalles[i].getItem(),
                detalles[i].getCantidad(),
                "S/ " + detalles[i].calcularSubtotal()
            });
        }
        labelTotal.setText("Total: S/ " + pedidoActual.calcularTotal());
    }

    private void finalizarPedido() {
        JOptionPane.showMessageDialog(this, "Pedido #" + pedidoActual.getNumero() + " registrado. Estado: " + pedidoActual.getEstado());
        dispose();
    }
}

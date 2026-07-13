package vista;

import com.restaurante.*;

public class RegistroPedidoIU extends javax.swing.JFrame {

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
    private javax.swing.table.DefaultTableModel modeloTabla;

    public RegistroPedidoIU(Cajero cajero, GestorClientes gestorClientes, GestorProductos gestorProductos,
            GestorCombos gestorCombos, GestorPedidos gestorPedidos, GestorEmpleados gestorEmpleados) {
        this.cajero = cajero;
        this.gestorClientes = gestorClientes;
        this.gestorProductos = gestorProductos;
        this.gestorCombos = gestorCombos;
        this.gestorPedidos = gestorPedidos;
        this.gestorEmpleados = gestorEmpleados;
        initComponents();

        clientesDisponibles = gestorClientes.listarTodos();
        for (int i = 0; i < clientesDisponibles.length; i++) {
            cboCliente.addItem(clientesDisponibles[i].getDniRuc() + " - " + clientesDisponibles[i].getNombres() + " " + clientesDisponibles[i].getApellidos());
        }

        productosDisponibles = gestorProductos.listarTodos();
        for (int i = 0; i < productosDisponibles.length; i++) {
            cboProducto.addItem(productosDisponibles[i].getCodigo() + " - " + productosDisponibles[i].getNombre() + " - S/ " + productosDisponibles[i].getPrecioBase());
        }

        combosDisponibles = gestorCombos.listarTodos();
        for (int i = 0; i < combosDisponibles.length; i++) {
            cboCombo.addItem(combosDisponibles[i].getNombre() + " - S/ " + combosDisponibles[i].getPrecioPromocional());
        }

        habilitarSeccionItems(false);
        modeloTabla = (javax.swing.table.DefaultTableModel) tablaItems.getModel();
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
            javax.swing.JOptionPane.showMessageDialog(this, "No hay clientes registrados", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
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
                javax.swing.JOptionPane.showMessageDialog(this, "No hay repartidores disponibles en este momento", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }

        javax.swing.JOptionPane.showMessageDialog(this, "Pedido #" + pedidoActual.getNumero() + " iniciado");
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
            javax.swing.JOptionPane.showMessageDialog(this, "No hay productos registrados", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
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
            javax.swing.JOptionPane.showMessageDialog(this, "No hay combos registrados", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
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

    private int leerCantidad(javax.swing.JTextField campo) {
        try {
            int cantidad = Integer.parseInt(campo.getText());
            if (cantidad <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            return cantidad;
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "La cantidad debe ser un numero entero", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
        javax.swing.JOptionPane.showMessageDialog(this, "Pedido #" + pedidoActual.getNumero() + " registrado. Estado: " + pedidoActual.getEstado());
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCliente = new javax.swing.JLabel();
        cboCliente = new javax.swing.JComboBox();
        jLabelTipoEntrega = new javax.swing.JLabel();
        cboTipoEntrega = new javax.swing.JComboBox();
        btnIniciarPedido = new javax.swing.JButton();
        jLabelProducto = new javax.swing.JLabel();
        cboProducto = new javax.swing.JComboBox();
        txtCantidadProducto = new javax.swing.JTextField();
        btnAgregarProducto = new javax.swing.JButton();
        jLabelCombo = new javax.swing.JLabel();
        cboCombo = new javax.swing.JComboBox();
        txtCantidadCombo = new javax.swing.JTextField();
        btnAgregarCombo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaItems = new javax.swing.JTable();
        labelTotal = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de un Nuevo Pedido");

        jLabelCliente.setText("Cliente:");

        cboTipoEntrega.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Delivery", "Recojo en Tienda" }));

        jLabelTipoEntrega.setText("Tipo de Entrega:");

        btnIniciarPedido.setText("Iniciar Pedido");
        btnIniciarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarPedidoActionPerformed(evt);
            }
        });

        jLabelProducto.setText("Producto:");

        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        jLabelCombo.setText("Combo:");

        btnAgregarCombo.setText("Agregar Combo");
        btnAgregarCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarComboActionPerformed(evt);
            }
        });

        tablaItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Cantidad", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaItems);

        labelTotal.setText("Total: S/ 0.0");

        btnFinalizar.setText("Finalizar Pedido");
        btnFinalizar.setEnabled(false);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCliente, 0, 220, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelTipoEntrega)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIniciarPedido))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboProducto, 0, 250, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarProducto))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCombo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCombo, 0, 250, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarCombo))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCliente)
                    .addComponent(cboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTipoEntrega)
                    .addComponent(cboTipoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciarPedido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProducto)
                    .addComponent(cboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCombo)
                    .addComponent(cboCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarCombo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotal)
                    .addComponent(btnFinalizar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarPedidoActionPerformed(java.awt.event.ActionEvent evt) {
        iniciarPedido();
    }

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {
        agregarProducto();
    }

    private void btnAgregarComboActionPerformed(java.awt.event.ActionEvent evt) {
        agregarCombo();
    }

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {
        finalizarPedido();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCombo;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnIniciarPedido;
    private javax.swing.JComboBox cboCliente;
    private javax.swing.JComboBox cboCombo;
    private javax.swing.JComboBox cboProducto;
    private javax.swing.JComboBox cboTipoEntrega;
    private javax.swing.JLabel jLabelCliente;
    private javax.swing.JLabel jLabelCombo;
    private javax.swing.JLabel jLabelProducto;
    private javax.swing.JLabel jLabelTipoEntrega;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTable tablaItems;
    private javax.swing.JTextField txtCantidadCombo;
    private javax.swing.JTextField txtCantidadProducto;
    // End of variables declaration//GEN-END:variables
}

package vista;

import com.restaurante.*;

public class RegistroComboIU extends javax.swing.JFrame {

    private GestorCombos gestorCombos;
    private GestorProductos gestorProductos;

    private Producto[] productosDisponibles;
    private Combo comboActual;

    public RegistroComboIU(GestorCombos gestorCombos, GestorProductos gestorProductos) {
        this.gestorCombos = gestorCombos;
        this.gestorProductos = gestorProductos;
        initComponents();

        productosDisponibles = gestorProductos.listarTodos();
        for (int i = 0; i < productosDisponibles.length; i++) {
            cboProducto.addItem(productosDisponibles[i].getCodigo() + " - " + productosDisponibles[i].getNombre());
        }
        habilitarSeccionProductos(false);
        modeloTabla = (javax.swing.table.DefaultTableModel) tablaProductos.getModel();
    }

    private javax.swing.table.DefaultTableModel modeloTabla;

    private void habilitarSeccionProductos(boolean habilitado) {
        cboProducto.setEnabled(habilitado);
        btnAgregarProducto.setEnabled(habilitado);
    }

    private void iniciarCombo() {
        if (txtNombre.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "El nombre del combo es obligatorio", "Datos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        double precioPromocional;
        try {
            precioPromocional = Double.parseDouble(txtPrecioPromocional.getText());
            if (precioPromocional <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "El precio promocional debe ser mayor a 0", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "El precio promocional debe ser un numero valido", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (productosDisponibles.length == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay productos registrados para armar un combo", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
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
            javax.swing.JOptionPane.showMessageDialog(this, "El combo ya tiene el maximo de 4 productos", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        modeloTabla.addRow(new Object[]{producto.getNombre(), "S/ " + producto.getPrecioBase()});
        if (comboActual.getCantidadProductos() == 4) {
            habilitarSeccionProductos(false);
        }
    }

    private void finalizarCombo() {
        if (comboActual.getCantidadProductos() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Agrega al menos un producto al combo", "Datos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        gestorCombos.agregar(comboActual);
        javax.swing.JOptionPane.showMessageDialog(this, "Combo grabado correctamente");
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabelPrecio = new javax.swing.JLabel();
        txtPrecioPromocional = new javax.swing.JTextField();
        btnIniciarCombo = new javax.swing.JButton();
        cboProducto = new javax.swing.JComboBox();
        btnAgregarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        btnFinalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de un Nuevo Combo");

        jLabelNombre.setText("Nombre:");

        jLabelPrecio.setText("Precio Promocional:");

        btnIniciarCombo.setText("Crear Combo");
        btnIniciarCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarComboActionPerformed(evt);
            }
        });

        btnAgregarProducto.setText("Agregar Producto al Combo");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio Base"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        btnFinalizar.setText("Finalizar Combo");
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
                        .addComponent(jLabelNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioPromocional, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIniciarCombo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboProducto, 0, 250, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarProducto))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFinalizar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPrecio)
                    .addComponent(txtPrecioPromocional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciarCombo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinalizar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarComboActionPerformed(java.awt.event.ActionEvent evt) {
        iniciarCombo();
    }

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {
        agregarProducto();
    }

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {
        finalizarCombo();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnIniciarCombo;
    private javax.swing.JComboBox cboProducto;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioPromocional;
    // End of variables declaration//GEN-END:variables
}

package vista;

import com.restaurante.*;

public class ListaEntregasIU extends javax.swing.JFrame {

    private Repartidor repartidor;
    private GestorPedidos gestorPedidos;
    private javax.swing.table.DefaultTableModel modeloTabla;

    public ListaEntregasIU(Repartidor repartidor, GestorPedidos gestorPedidos) {
        this.repartidor = repartidor;
        this.gestorPedidos = gestorPedidos;
        initComponents();
        modeloTabla = (javax.swing.table.DefaultTableModel) tablaEntregas.getModel();
        cargarEntregas();
    }

    private void cargarEntregas() {
        modeloTabla.setRowCount(0);
        Pedido[] pedidos = gestorPedidos.listarTodos();
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i].getRepartidor() == repartidor) {
                modeloTabla.addRow(new Object[]{
                    pedidos[i].getNumero(),
                    pedidos[i].getCliente().getNombres() + " " + pedidos[i].getCliente().getApellidos(),
                    pedidos[i].getEstado(),
                    "S/ " + pedidos[i].calcularTotal()
                });
            }
        }
    }

    private Pedido obtenerPedidoSeleccionado() {
        int fila = tablaEntregas.getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un pedido de la tabla", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return null;
        }
        int numero = (int) modeloTabla.getValueAt(fila, 0);
        return gestorPedidos.buscarPorNumero(numero);
    }

    private void marcarEntregado() {
        Pedido pedido = obtenerPedidoSeleccionado();
        if (pedido == null) {
            return;
        }
        if (pedido.getEstado().equals("Entregado")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Este pedido ya esta entregado", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        pedido.cambiarEstado("Entregado");
        repartidor.setDisponible(true);
        javax.swing.JOptionPane.showMessageDialog(this, "Pedido #" + pedido.getNumero() + " marcado como Entregado");
        cargarEntregas();
    }

    private void reportarIncidente() {
        Pedido pedido = obtenerPedidoSeleccionado();
        if (pedido == null) {
            return;
        }
        String descripcion = javax.swing.JOptionPane.showInputDialog(this, "Descripcion del incidente:");
        if (descripcion == null || descripcion.isEmpty()) {
            return;
        }
        String montoTexto = javax.swing.JOptionPane.showInputDialog(this, "Monto del recargo (S/):");
        if (montoTexto == null || montoTexto.isEmpty()) {
            return;
        }
        double monto;
        try {
            monto = Double.parseDouble(montoTexto);
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "El monto debe ser un numero", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        pedido.agregarIncidente(new Incidente(descripcion, monto, pedido.getFecha()));
        javax.swing.JOptionPane.showMessageDialog(this, "Incidente registrado en el pedido #" + pedido.getNumero());
        cargarEntregas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEntregas = new javax.swing.JTable();
        btnMarcarEntregado = new javax.swing.JButton();
        btnReportarIncidente = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mis Entregas");

        jLabel1.setText("Lista de Pedidos Asignados para Entrega");

        tablaEntregas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Cliente", "Estado", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaEntregas);

        btnMarcarEntregado.setText("Marcar Entregado");
        btnMarcarEntregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarEntregadoActionPerformed(evt);
            }
        });

        btnReportarIncidente.setText("Reportar Incidente");
        btnReportarIncidente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportarIncidenteActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnMarcarEntregado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReportarIncidente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMarcarEntregado)
                    .addComponent(btnReportarIncidente)
                    .addComponent(btnCerrar))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMarcarEntregadoActionPerformed(java.awt.event.ActionEvent evt) {
        marcarEntregado();
    }

    private void btnReportarIncidenteActionPerformed(java.awt.event.ActionEvent evt) {
        reportarIncidente();
    }

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnMarcarEntregado;
    private javax.swing.JButton btnReportarIncidente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEntregas;
    // End of variables declaration//GEN-END:variables
}

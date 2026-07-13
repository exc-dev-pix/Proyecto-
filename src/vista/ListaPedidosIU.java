package vista;

import com.restaurante.*;

public class ListaPedidosIU extends javax.swing.JFrame {

    private GestorPedidos gestorPedidos;
    private javax.swing.table.DefaultTableModel modeloTabla;

    public ListaPedidosIU(GestorPedidos gestorPedidos) {
        this.gestorPedidos = gestorPedidos;
        initComponents();
        modeloTabla = (javax.swing.table.DefaultTableModel) tablaPedidos.getModel();
        cargarPedidos();
    }

    private void cargarPedidos() {
        modeloTabla.setRowCount(0);
        Pedido[] pedidos = gestorPedidos.listarTodos();
        for (int i = 0; i < pedidos.length; i++) {
            modeloTabla.addRow(new Object[]{
                pedidos[i].getNumero(),
                pedidos[i].getCliente().getNombres() + " " + pedidos[i].getCliente().getApellidos(),
                pedidos[i].getTipoEntrega(),
                pedidos[i].getEstado(),
                "S/ " + pedidos[i].calcularTotal()
            });
        }
    }

    private void marcarEntregado() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un pedido de la tabla", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        int numero = (int) modeloTabla.getValueAt(fila, 0);
        Pedido pedido = gestorPedidos.buscarPorNumero(numero);
        if (pedido.getEstado().equals("Entregado")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Este pedido ya esta entregado", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        pedido.cambiarEstado("Entregado");
        if (pedido.getRepartidor() != null) {
            pedido.getRepartidor().setDisponible(true);
        }
        cargarPedidos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        btnMarcarEntregado = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Pedidos");

        jLabel1.setText("Lista de Pedidos registrados en el Sistema");

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Cliente", "Tipo Entrega", "Estado", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPedidos);

        btnMarcarEntregado.setText("Marcar Entregado");
        btnMarcarEntregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarEntregadoActionPerformed(evt);
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
                .addGap(150, 150, 150)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnMarcarEntregado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnCerrar))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMarcarEntregadoActionPerformed(java.awt.event.ActionEvent evt) {
        marcarEntregado();
    }

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnMarcarEntregado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPedidos;
    // End of variables declaration//GEN-END:variables
}

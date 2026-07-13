package vista;

import com.restaurante.*;

public class RepartidorMenuIU extends javax.swing.JFrame {

    private Repartidor repartidor;
    private GestorPedidos gestorPedidos;

    public RepartidorMenuIU(Repartidor repartidor, GestorPedidos gestorPedidos) {
        this.repartidor = repartidor;
        this.gestorPedidos = gestorPedidos;
        initComponents();
        jLabelBienvenida.setText("Bienvenido, " + repartidor.getNombres() + " (Repartidor)");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemSalir = new javax.swing.JMenuItem();
        menuPedidos = new javax.swing.JMenu();
        itemMisEntregas = new javax.swing.JMenuItem();
        jLabelBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de Repartidor");

        menuArchivo.setText("Archivo");

        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemSalir);

        menuBar1.add(menuArchivo);

        menuPedidos.setText("Pedidos");

        itemMisEntregas.setText("Mis Entregas");
        itemMisEntregas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMisEntregasActionPerformed(evt);
            }
        });
        menuPedidos.add(itemMisEntregas);

        menuBar1.add(menuPedidos);

        setJMenuBar(menuBar1);

        jLabelBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBienvenida.setText("Bienvenido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabelBienvenida)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void itemMisEntregasActionPerformed(java.awt.event.ActionEvent evt) {
        new ListaEntregasIU(repartidor, gestorPedidos).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelBienvenida;
    private javax.swing.JMenuItem itemMisEntregas;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuBar menuBar1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuPedidos;
    // End of variables declaration//GEN-END:variables
}

package vista;

import com.restaurante.*;

public class CajeroMenuIU extends javax.swing.JFrame {

    private Cajero cajero;
    private GestorClientes gestorClientes;
    private GestorProductos gestorProductos;
    private GestorCombos gestorCombos;
    private GestorPedidos gestorPedidos;
    private GestorEmpleados gestorEmpleados;

    public CajeroMenuIU(Cajero cajero, GestorClientes gestorClientes, GestorProductos gestorProductos,
            GestorCombos gestorCombos, GestorPedidos gestorPedidos, GestorEmpleados gestorEmpleados) {
        this.cajero = cajero;
        this.gestorClientes = gestorClientes;
        this.gestorProductos = gestorProductos;
        this.gestorCombos = gestorCombos;
        this.gestorPedidos = gestorPedidos;
        this.gestorEmpleados = gestorEmpleados;
        initComponents();
        jLabelBienvenida.setText("Bienvenido, " + cajero.getNombres() + " (Cajero)");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemSalir = new javax.swing.JMenuItem();
        menuClientes = new javax.swing.JMenu();
        itemNuevoCliente = new javax.swing.JMenuItem();
        itemListaClientes = new javax.swing.JMenuItem();
        menuPedidos = new javax.swing.JMenu();
        itemNuevoPedido = new javax.swing.JMenuItem();
        itemListaPedidos = new javax.swing.JMenuItem();
        jLabelBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de Cajero");

        menuArchivo.setText("Archivo");

        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemSalir);

        menuBar1.add(menuArchivo);

        menuClientes.setText("Clientes");

        itemNuevoCliente.setText("Nuevo Cliente");
        itemNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoClienteActionPerformed(evt);
            }
        });
        menuClientes.add(itemNuevoCliente);

        itemListaClientes.setText("Lista de Clientes");
        itemListaClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListaClientesActionPerformed(evt);
            }
        });
        menuClientes.add(itemListaClientes);

        menuBar1.add(menuClientes);

        menuPedidos.setText("Pedidos");

        itemNuevoPedido.setText("Nuevo Pedido");
        itemNuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoPedidoActionPerformed(evt);
            }
        });
        menuPedidos.add(itemNuevoPedido);

        itemListaPedidos.setText("Lista de Pedidos");
        itemListaPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListaPedidosActionPerformed(evt);
            }
        });
        menuPedidos.add(itemListaPedidos);

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

    private void itemNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {
        new RegistroClienteIU(gestorClientes).setVisible(true);
    }

    private void itemListaClientesActionPerformed(java.awt.event.ActionEvent evt) {
        new ListaClientesIU(gestorClientes).setVisible(true);
    }

    private void itemNuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {
        new RegistroPedidoIU(cajero, gestorClientes, gestorProductos, gestorCombos, gestorPedidos, gestorEmpleados).setVisible(true);
    }

    private void itemListaPedidosActionPerformed(java.awt.event.ActionEvent evt) {
        new ListaPedidosIU(gestorPedidos).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelBienvenida;
    private javax.swing.JMenuItem itemListaClientes;
    private javax.swing.JMenuItem itemListaPedidos;
    private javax.swing.JMenuItem itemNuevoCliente;
    private javax.swing.JMenuItem itemNuevoPedido;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuBar menuBar1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuClientes;
    private javax.swing.JMenu menuPedidos;
    // End of variables declaration//GEN-END:variables
}

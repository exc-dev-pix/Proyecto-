package vista;

import com.restaurante.*;

public class AdminMenuIU extends javax.swing.JFrame {

    private Empleado empleado;
    private GestorEmpleados gestorEmpleados;
    private GestorProductos gestorProductos;
    private GestorClientes gestorClientes;
    private GestorCombos gestorCombos;
    private GestorPedidos gestorPedidos;
    private GestorReportes gestorReportes;

    public AdminMenuIU(Empleado empleado, GestorEmpleados gestorEmpleados, GestorProductos gestorProductos,
            GestorClientes gestorClientes, GestorCombos gestorCombos, GestorPedidos gestorPedidos,
            GestorReportes gestorReportes) {
        this.empleado = empleado;
        this.gestorEmpleados = gestorEmpleados;
        this.gestorProductos = gestorProductos;
        this.gestorClientes = gestorClientes;
        this.gestorCombos = gestorCombos;
        this.gestorPedidos = gestorPedidos;
        this.gestorReportes = gestorReportes;
        initComponents();
        jLabelBienvenida.setText("Bienvenido, " + empleado.getNombres() + " (Administrador)");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemSalir = new javax.swing.JMenuItem();
        menuEmpleados = new javax.swing.JMenu();
        itemNuevoEmpleado = new javax.swing.JMenuItem();
        itemListaEmpleados = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenu();
        itemNuevoProducto = new javax.swing.JMenuItem();
        itemListaProductos = new javax.swing.JMenuItem();
        menuCombos = new javax.swing.JMenu();
        itemNuevoCombo = new javax.swing.JMenuItem();
        itemListaCombos = new javax.swing.JMenuItem();
        menuReportes = new javax.swing.JMenu();
        itemVerReportes = new javax.swing.JMenuItem();
        jLabelBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de Administrador");

        menuArchivo.setText("Archivo");

        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemSalir);

        menuBar1.add(menuArchivo);

        menuEmpleados.setText("Empleados");

        itemNuevoEmpleado.setText("Nuevo Empleado");
        itemNuevoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoEmpleadoActionPerformed(evt);
            }
        });
        menuEmpleados.add(itemNuevoEmpleado);

        itemListaEmpleados.setText("Lista de Empleados");
        itemListaEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListaEmpleadosActionPerformed(evt);
            }
        });
        menuEmpleados.add(itemListaEmpleados);

        menuBar1.add(menuEmpleados);

        menuProductos.setText("Productos");

        itemNuevoProducto.setText("Nuevo Producto");
        itemNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoProductoActionPerformed(evt);
            }
        });
        menuProductos.add(itemNuevoProducto);

        itemListaProductos.setText("Lista de Productos");
        itemListaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListaProductosActionPerformed(evt);
            }
        });
        menuProductos.add(itemListaProductos);

        menuBar1.add(menuProductos);

        menuCombos.setText("Combos");

        itemNuevoCombo.setText("Nuevo Combo");
        itemNuevoCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoComboActionPerformed(evt);
            }
        });
        menuCombos.add(itemNuevoCombo);

        itemListaCombos.setText("Lista de Combos");
        itemListaCombos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListaCombosActionPerformed(evt);
            }
        });
        menuCombos.add(itemListaCombos);

        menuBar1.add(menuCombos);

        menuReportes.setText("Reportes");

        itemVerReportes.setText("Ver Reportes");
        itemVerReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVerReportesActionPerformed(evt);
            }
        });
        menuReportes.add(itemVerReportes);

        menuBar1.add(menuReportes);

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

    private void itemNuevoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {
        new RegistroEmpleadoIU(gestorEmpleados).setVisible(true);
    }

    private void itemListaEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {
        new ListaEmpleadosIU(gestorEmpleados).setVisible(true);
    }

    private void itemNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {
        new RegistroProductoIU(gestorProductos).setVisible(true);
    }

    private void itemListaProductosActionPerformed(java.awt.event.ActionEvent evt) {
        new ListaProductosIU(gestorProductos).setVisible(true);
    }

    private void itemNuevoComboActionPerformed(java.awt.event.ActionEvent evt) {
        new RegistroComboIU(gestorCombos, gestorProductos).setVisible(true);
    }

    private void itemListaCombosActionPerformed(java.awt.event.ActionEvent evt) {
        new ListaCombosIU(gestorCombos).setVisible(true);
    }

    private void itemVerReportesActionPerformed(java.awt.event.ActionEvent evt) {
        new ReportesIU(gestorReportes, gestorEmpleados).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelBienvenida;
    private javax.swing.JMenuItem itemListaCombos;
    private javax.swing.JMenuItem itemListaEmpleados;
    private javax.swing.JMenuItem itemListaProductos;
    private javax.swing.JMenuItem itemNuevoCombo;
    private javax.swing.JMenuItem itemNuevoEmpleado;
    private javax.swing.JMenuItem itemNuevoProducto;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemVerReportes;
    private javax.swing.JMenuBar menuBar1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuCombos;
    private javax.swing.JMenu menuEmpleados;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenu menuReportes;
    // End of variables declaration//GEN-END:variables
}

package vista;

import com.restaurante.*;

public class ReportesIU extends javax.swing.JFrame {

    private GestorReportes gestorReportes;
    private GestorEmpleados gestorEmpleados;

    public ReportesIU(GestorReportes gestorReportes, GestorEmpleados gestorEmpleados) {
        this.gestorReportes = gestorReportes;
        this.gestorEmpleados = gestorEmpleados;
        initComponents();
    }

    private void generarProductividad() {
        txtSalida.setText(capturarSalida(() -> gestorReportes.reporteProductividad(gestorEmpleados)));
    }

    private void generarIngresos() {
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        java.util.Date desde;
        java.util.Date hasta;
        try {
            desde = formato.parse(txtDesde.getText());
            hasta = formato.parse(txtHasta.getText());
        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Las fechas deben tener el formato dd/mm/aaaa", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtSalida.setText(capturarSalida(() -> gestorReportes.reporteIngresos(desde, hasta)));
    }

    private String capturarSalida(Runnable accion) {
        java.io.PrintStream original = System.out;
        java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(buffer));
        try {
            accion.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnProductividad = new javax.swing.JButton();
        jLabelDesde = new javax.swing.JLabel();
        txtDesde = new javax.swing.JTextField();
        jLabelHasta = new javax.swing.JLabel();
        txtHasta = new javax.swing.JTextField();
        btnIngresos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes Gerenciales");

        btnProductividad.setText("Reporte de Productividad");
        btnProductividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductividadActionPerformed(evt);
            }
        });

        jLabelDesde.setText("Desde (dd/mm/aaaa):");

        jLabelHasta.setText("Hasta (dd/mm/aaaa):");

        btnIngresos.setText("Reporte de Ingresos");
        btnIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresosActionPerformed(evt);
            }
        });

        txtSalida.setColumns(20);
        txtSalida.setRows(5);
        txtSalida.setEditable(false);
        jScrollPane1.setViewportView(txtSalida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProductividad)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDesde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHasta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIngresos))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProductividad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDesde)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHasta)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductividadActionPerformed(java.awt.event.ActionEvent evt) {
        generarProductividad();
    }

    private void btnIngresosActionPerformed(java.awt.event.ActionEvent evt) {
        generarIngresos();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresos;
    private javax.swing.JButton btnProductividad;
    private javax.swing.JLabel jLabelDesde;
    private javax.swing.JLabel jLabelHasta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}

package vista;

import com.restaurante.*;

public class LoginIU extends javax.swing.JFrame {

    private GestorEmpleados gestorEmpleados;
    private GestorProductos gestorProductos;
    private GestorClientes gestorClientes;
    private GestorCombos gestorCombos;
    private GestorPedidos gestorPedidos;
    private GestorReportes gestorReportes;

    public LoginIU() {
        inicializarGestores();
        initComponents();
    }

    private void inicializarGestores() {
        Vehiculos moto = new Vehiculos("ABC-123", "Moto");

        Cajero cajero = new Cajero("10000002", "Joaquin", "Perez", "jperez", "cajero123");
        Repartidor repartidor = new Repartidor("10000003", "Italo", "Gomez", "igomez", "repartidor123", moto);

        gestorEmpleados = new GestorEmpleados();
        gestorEmpleados.agregar(new Admin("10000001", "Diego", "Calderon", "dcalderon", "admin123"));
        gestorEmpleados.agregar(cajero);
        gestorEmpleados.agregar(repartidor);

        gestorClientes = new GestorClientes();
        Cliente cliente = new Cliente("70000001", "Maria", "Lopez", "Av. Siempre Viva 123", "987654321");
        gestorClientes.agregar(cliente);

        gestorProductos = new GestorProductos();
        Producto gaseosa = new Producto("P001", "Gaseosa 500ml", 5.0, "Bebida");
        Producto hamburguesa = new Producto("P002", "Hamburguesa Clasica", 12.0, "Comida");
        gestorProductos.agregar(gaseosa);
        gestorProductos.agregar(hamburguesa);

        gestorCombos = new GestorCombos();

        gestorPedidos = new GestorPedidos();
        Pedido pedidoDemo = gestorPedidos.crearPedido(cliente, "Delivery", cajero);
        pedidoDemo.asignarRepartidor(repartidor);
        pedidoDemo.agregarDetalle(new DetallePedido(hamburguesa, 2));
        pedidoDemo.agregarDetalle(new DetallePedido(gaseosa, 1));

        gestorReportes = new GestorReportes(gestorPedidos);
    }

    private void ingresar() {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        Empleado empleado = gestorEmpleados.autenticar(usuario, password);
        if (empleado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Usuario o password incorrectos", "Error de acceso", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (empleado instanceof Admin) {
            new AdminMenuIU(empleado, gestorEmpleados, gestorProductos, gestorClientes, gestorCombos, gestorPedidos, gestorReportes).setVisible(true);
        } else if (empleado instanceof Cajero) {
            new CajeroMenuIU((Cajero) empleado, gestorClientes, gestorProductos, gestorCombos, gestorPedidos, gestorEmpleados).setVisible(true);
        } else if (empleado instanceof Repartidor) {
            new RepartidorMenuIU((Repartidor) empleado, gestorPedidos).setVisible(true);
        }

        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsuario = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestion - Restaurante Delivery POO - Login");
        setResizable(false);

        jLabelUsuario.setText("Usuario:");

        jLabelPassword.setText("Password:");

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUsuario)
                    .addComponent(jLabelPassword)
                )
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtPassword)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnIngresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {
        ingresar();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new LoginIU().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

package com.restaurante;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ReportesIU extends JFrame {

    private GestorReportes gestorReportes;
    private GestorEmpleados gestorEmpleados;

    private JTextField txtDesde;
    private JTextField txtHasta;
    private JTextArea txtSalida;

    public ReportesIU(GestorReportes gestorReportes, GestorEmpleados gestorEmpleados) {
        this.gestorReportes = gestorReportes;
        this.gestorEmpleados = gestorEmpleados;
        initComponents();
    }

    private void initComponents() {
        setTitle("Reportes Gerenciales");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 450);
        setLocationRelativeTo(null);

        JButton btnProductividad = new JButton("Reporte de Productividad");
        btnProductividad.addActionListener(evt -> generarProductividad());

        JPanel panelIngresos = new JPanel(new GridLayout(1, 5, 5, 5));
        panelIngresos.add(new JLabel("Desde (dd/mm/aaaa):"));
        txtDesde = new JTextField();
        panelIngresos.add(txtDesde);
        panelIngresos.add(new JLabel("Hasta (dd/mm/aaaa):"));
        txtHasta = new JTextField();
        panelIngresos.add(txtHasta);
        JButton btnIngresos = new JButton("Reporte de Ingresos");
        btnIngresos.addActionListener(evt -> generarIngresos());
        panelIngresos.add(btnIngresos);

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        panelSuperior.add(btnProductividad, BorderLayout.NORTH);
        panelSuperior.add(panelIngresos, BorderLayout.SOUTH);

        txtSalida = new JTextArea();
        txtSalida.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtSalida);

        setLayout(new BorderLayout(5, 5));
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void generarProductividad() {
        txtSalida.setText(capturarSalida(() -> gestorReportes.reporteProductividad(gestorEmpleados)));
    }

    private void generarIngresos() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        Date desde;
        Date hasta;
        try {
            desde = formato.parse(txtDesde.getText());
            hasta = formato.parse(txtHasta.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Las fechas deben tener el formato dd/mm/aaaa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtSalida.setText(capturarSalida(() -> gestorReportes.reporteIngresos(desde, hasta)));
    }

    private String capturarSalida(Runnable accion) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            accion.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }
}

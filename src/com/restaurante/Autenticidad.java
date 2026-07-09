package com.restaurante;

/**
 * Contrato de autenticación (RF10) para los Empleados del sistema.
 */
public interface Autenticidad {

    boolean login(String usuario, String contrasena);

    void logout();
}

package com.restaurante;

public class Admin extends Empleado {

    public Admin(String dni, String nombres, String apellidos, String login, String password) {
        super(dni, nombres, apellidos, login, password, "Administrador");
    }

    @Override
    public String toString() {
        return super.toString() + " - Panel de administracion";
    }
}

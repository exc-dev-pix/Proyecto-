package com.restaurante;

public class Cajero extends Empleado {

    public Cajero(String dni, String nombres, String apellidos, String login, String password) {
        super(dni, nombres, apellidos, login, password, "Cajero");
    }

    @Override
    public String toString() {
        return super.toString() + " - Atencion de pedidos";
    }
}

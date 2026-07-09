package com.restaurante;

public class Repartidor extends Empleado {

    private boolean disponible;
    private Vehiculos vehiculo;

    public Repartidor(String dni, String nombres, String apellidos, String login, String password, Vehiculos vehiculo) {
        super(dni, nombres, apellidos, login, password, "Repartidor");
        this.disponible = true;
        this.vehiculo = vehiculo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void reportarIncidente(String descripcion, double monto) {
        System.out.println("Incidente reportado por " + nombres + " " + apellidos + ": " + descripcion + " (S/ " + monto + ")");
    }

    @Override
    public String toString() {
        return super.toString() + " - Disponible: " + (disponible ? "Si" : "No") + ", Vehiculo: " + vehiculo;
    }
}

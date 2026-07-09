package com.restaurante;

public class Main {

    public static void main(String[] args) {
        System.out.println("Sistema de Gestion - Restaurante Delivery POO");

        Vehiculos moto = new Vehiculos("ABC-123", "Moto");

        Empleado[] empleados = new Empleado[3];
        empleados[0] = new Admin("10000001", "Diego", "Calderon", "dcalderon", "admin123");
        empleados[1] = new Cajero("10000002", "Joaquin", "Perez", "jperez", "cajero123");
        empleados[2] = new Repartidor("10000003", "Italo", "Gomez", "igomez", "repartidor123", moto);

        System.out.println("\nEmpleados registrados:");
        for (int i = 0; i < empleados.length; i++) {
            System.out.println("- " + empleados[i]);
        }

        boolean acceso = empleados[0].login("dcalderon", "admin123");
        System.out.println("\nLogin de " + empleados[0].getNombres() + ": " + acceso);
    }
}

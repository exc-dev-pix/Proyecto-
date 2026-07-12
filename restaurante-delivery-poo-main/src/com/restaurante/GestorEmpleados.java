package com.restaurante;

public class GestorEmpleados {

    private Empleado[] empleados;
    private int cantidadEmpleados;

    public GestorEmpleados() {
        this.empleados = new Empleado[4];
        this.cantidadEmpleados = 0;
    }

    public void agregar(Empleado empleado) {
        if (cantidadEmpleados == empleados.length) {
            Empleado[] nuevoArreglo = new Empleado[empleados.length * 2];
            for (int i = 0; i < empleados.length; i++) {
                nuevoArreglo[i] = empleados[i];
            }
            empleados = nuevoArreglo;
        }
        empleados[cantidadEmpleados] = empleado;
        cantidadEmpleados++;
    }

    public Empleado autenticar(String usuario, String password) {
        for (int i = 0; i < cantidadEmpleados; i++) {
            if (empleados[i].login(usuario, password)) {
                return empleados[i];
            }
        }
        return null;
    }

    public Empleado buscarPorDni(String dni) {
        for (int i = 0; i < cantidadEmpleados; i++) {
            if (empleados[i].getDni().equals(dni)) {
                return empleados[i];
            }
        }
        return null;
    }

    public boolean eliminar(String dni) {
        for (int i = 0; i < cantidadEmpleados; i++) {
            if (empleados[i].getDni().equals(dni)) {
                for (int j = i; j < cantidadEmpleados - 1; j++) {
                    empleados[j] = empleados[j + 1];
                }
                empleados[cantidadEmpleados - 1] = null;
                cantidadEmpleados--;
                return true;
            }
        }
        return false;
    }

    public Empleado[] listarTodos() {
        Empleado[] copia = new Empleado[cantidadEmpleados];
        for (int i = 0; i < cantidadEmpleados; i++) {
            copia[i] = empleados[i];
        }
        return copia;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }
}

package com.restaurante;

public class GestorCombos {

    private Combo[] combos;
    private int cantidadCombos;

    public GestorCombos() {
        this.combos = new Combo[4];
        this.cantidadCombos = 0;
    }

    public void agregar(Combo combo) {
        if (cantidadCombos == combos.length) {
            Combo[] nuevoArreglo = new Combo[combos.length * 2];
            for (int i = 0; i < combos.length; i++) {
                nuevoArreglo[i] = combos[i];
            }
            combos = nuevoArreglo;
        }
        combos[cantidadCombos] = combo;
        cantidadCombos++;
    }

    public Combo buscarPorNombre(String nombre) {
        for (int i = 0; i < cantidadCombos; i++) {
            if (combos[i].getNombre().equals(nombre)) {
                return combos[i];
            }
        }
        return null;
    }

    public boolean eliminar(String nombre) {
        for (int i = 0; i < cantidadCombos; i++) {
            if (combos[i].getNombre().equals(nombre)) {
                for (int j = i; j < cantidadCombos - 1; j++) {
                    combos[j] = combos[j + 1];
                }
                combos[cantidadCombos - 1] = null;
                cantidadCombos--;
                return true;
            }
        }
        return false;
    }

    public Combo[] listarTodos() {
        Combo[] copia = new Combo[cantidadCombos];
        for (int i = 0; i < cantidadCombos; i++) {
            copia[i] = combos[i];
        }
        return copia;
    }

    public int getCantidadCombos() {
        return cantidadCombos;
    }
}

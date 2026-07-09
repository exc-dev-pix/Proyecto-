package com.restaurante;

public class Vehiculos {

    private String placa;
    private String tipo;
    private boolean operativo;

    public Vehiculos(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
        this.operativo = true;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isOperativo() {
        return operativo;
    }

    public void setOperativo(boolean operativo) {
        this.operativo = operativo;
    }

    @Override
    public String toString() {
        return tipo + " - Placa: " + placa + " (" + (operativo ? "Operativa" : "No operativa") + ")";
    }
}

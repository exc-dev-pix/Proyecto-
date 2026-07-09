package com.restaurante;

public abstract class Empleado implements Autenticidad {

    protected String dni;
    protected String nombres;
    protected String apellidos;
    protected String login;
    protected String password;
    protected String rol;

    private boolean sesionActiva;

    public Empleado(String dni, String nombres, String apellidos, String login, String password, String rol) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.login = login;
        this.password = password;
        this.rol = rol;
        this.sesionActiva = false;
    }

    public boolean autenticar(String passwordIngresado) {
        return password.equals(passwordIngresado);
    }

    @Override
    public boolean login(String usuario, String contrasena) {
        if (login.equals(usuario) && autenticar(contrasena)) {
            sesionActiva = true;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        sesionActiva = false;
    }

    public boolean isSesionActiva() {
        return sesionActiva;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos + " (" + rol + ")";
    }
}

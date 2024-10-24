/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Usuario {
    private String dni;
    private String nombre;
    private String contraseña;
    private int idRol;

    // Constructor vacío (necesario para algunos frameworks y casos)
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String dni, String nombre, String contraseña, int idRol) {
        this.dni = dni;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.idRol = idRol;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}

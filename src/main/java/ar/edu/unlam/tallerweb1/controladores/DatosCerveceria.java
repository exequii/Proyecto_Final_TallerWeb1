package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveza;

import java.util.LinkedList;
import java.util.List;

public class DatosCerveceria {

    private String nombre;
    private String direccion;
    private String barrio;
    private String name;

    private List<Cerveza> cervezas = new LinkedList<>();

    /****************************************************************/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Cerveza> getCervezas() {
        return cervezas;
    }

    public void setCervezas(List<Cerveza> cervezas) {
        this.cervezas = cervezas;
    }

    public void addCerveza(Cerveza cerveza){
        this.cervezas.add(cerveza);
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

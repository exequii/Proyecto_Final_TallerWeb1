package ar.edu.unlam.tallerweb1.controladores;

public class DatosRegistro {
    private String email;
    private String clave;
    private String repiteClave;
    private String rol;
    private String barrio;
    private String direccion;

    public DatosRegistro(){}

    public DatosRegistro(String email, String clave, String repiteClave) {
        this.email = email;
        this.clave = clave;
        this.repiteClave = repiteClave;
    }

    public DatosRegistro(String email, String clave, String repiteClave,String rol) {
        this.email = email;
        this.clave = clave;
        this.repiteClave = repiteClave;
        this.rol = rol;
    }

    public DatosRegistro(String email, String clave, String repiteClave,String rol,String barrio) {
        this.email = email;
        this.clave = clave;
        this.repiteClave = repiteClave;
        this.rol = rol;
        this.barrio = barrio;
    }

    public DatosRegistro(String email, String clave, String repiteClave,String rol,String barrio,String direccion) {
        this.email = email;
        this.clave = clave;
        this.repiteClave = repiteClave;
        this.rol = rol;
        this.barrio = barrio;
        this.direccion = direccion;
    }

    public DatosRegistro(String barrio) {
        this.barrio = barrio;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRepiteClave() {
        return repiteClave;
    }

    public void setRepiteClave(String repiteClave) {
        this.repiteClave = repiteClave;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

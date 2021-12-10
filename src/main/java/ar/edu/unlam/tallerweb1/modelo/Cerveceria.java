package ar.edu.unlam.tallerweb1.modelo;
import static org.hibernate.annotations.CascadeType.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@Entity
public class Cerveceria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private Integer capacidadTotalPorHora = 30;

    private Double promedioPuntuacion;
    private String barrio;

    private Double latitud;
    private Double longitud;

    private Boolean mostrar = true;

    private Boolean mejorPunteada = false;

//    @OneToMany(fetch = FetchType.LAZY)
//    @Cascade(value = ALL)
//    private List<Recomendacion> recomendaciones = new LinkedList<>();

    //volarlo
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = ALL)
    private List<Cerveza> cervezas = new LinkedList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = ALL)
    private List<Reserva> reservas = new LinkedList<>();

    @ManyToOne
    private Usuario propietario;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = ALL)
    private List<Notificacion> notificaciones = new LinkedList<>();

    public Cerveceria(String nombre) {
        this.nombre = nombre;
    }

    /**********************************************************/

    @Override
    public String toString() {
        return "Cerveceria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cerveceria that = (Cerveceria) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /******************************************************************/

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

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> eeservas) {
        this.reservas = reservas;
    }

    public void addReserva(Reserva reserva){
        this.reservas.add(reserva);
    }

    public Integer getCapacidadTotalPorHora() {
        return capacidadTotalPorHora;
    }

    public void setCapacidadTotalPorHora(Integer capacidadTotalPorHora) {
        this.capacidadTotalPorHora = capacidadTotalPorHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPromedioPuntuacion() {
        return promedioPuntuacion;
    }

    public void setPromedioPuntuacion(Double promedioPuntuacion) {
        this.promedioPuntuacion = promedioPuntuacion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Boolean getMejorPunteada() {
        return mejorPunteada;
    }

    public void setMejorPunteada(Boolean mejorPunteada) {
        this.mejorPunteada = mejorPunteada;
    }


    public Boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(Boolean mostrar) {
        this.mostrar = mostrar;
    }

    /*******************************************************************************/

    public Cerveceria(){

    }
    public Cerveceria(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Cerveceria(String nombre, String direccion, List<Cerveza> cervezas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cervezas = cervezas;
    }

    public Cerveceria(String nombre, String direccion, Usuario propietario) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.propietario = propietario;
    }
}

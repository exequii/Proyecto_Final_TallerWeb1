package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cerveza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String marca;
    private TipoCerveza tipo;

    @ManyToOne(optional = true)
    private Cerveceria cerveceria;

    /***********************************************************/

    @Override
    public String toString() {
        return "Cerveza{" +
                "nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", tipo=" + tipo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cerveza cerveza = (Cerveza) o;
        return id.equals(cerveza.id);
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

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoCerveza getTipo() {
        return tipo;
    }

    public void setTipo(TipoCerveza tipo) {
        this.tipo = tipo;
    }

    public Cerveceria getCerveceria() {
        return cerveceria;
    }

    public void setCerveceria(Cerveceria cerveceria) {
        this.cerveceria = cerveceria;
    }

    /********************************************************************/

    public Cerveza() {

    }

    public Cerveza(String nombre, String marca, TipoCerveza tipo) {
        this.nombre = nombre;
        this.marca = marca;
        this.tipo = tipo;
    }

    public Cerveza(String nombre, String marca, TipoCerveza tipo, Cerveceria cerveceria) {
        this.nombre = nombre;
        this.marca = marca;
        this.tipo = tipo;
        this.cerveceria = cerveceria;
    }
}

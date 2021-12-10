package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cerveceria cerveceria;

    public Feedback(Integer puntuacion, String comentario, Cerveceria cerveceria, String cerveza, Usuario usuario) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.cerveceria = cerveceria;
        this.cerveza = cerveza;
        this.usuario = usuario;
    }

    private Integer puntuacion; //minimo 1 maximo 5
    private String comentario;
    private Date fecha;
    private String cerveza;

    //@OneToOne(cascade = CascadeType.ALL)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Usuario usuario;


    public Feedback(Integer puntuacion, String comentario, Date fecha, Cerveceria cerveceria, String cerveza, Usuario usuario) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.cerveceria = cerveceria;
        this.cerveza = cerveza;
        this.usuario = usuario;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id.equals(feedback.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", puntuacion=" + puntuacion +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                ", cerveceria='" + cerveceria + '\'' +
                ", cerveza='" + cerveza + '\'' +
                ", usuario=" + usuario +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Cerveceria getCerveceria() {
        return cerveceria;
    }

    public void setCerveceria(Cerveceria cerveceria) {
        this.cerveceria = cerveceria;
    }

    public String getCerveza() {
        return cerveza;
    }

    public void setCerveza(String cerveza) {
        this.cerveza = cerveza;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacionCerveceria) {
        this.puntuacion = puntuacionCerveceria;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Feedback() {

    }
}

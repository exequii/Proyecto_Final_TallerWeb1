package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import javax.persistence.*;

@Entity
public class DatosFeedback {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String cerveceria;
    private Integer puntuacion;
    private String cerveza;
    private String comentario;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Usuario usuario;




    /*******************************************************************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCerveceria() {
        return cerveceria;
    }

    public void setCerveceria(String cerveceria) {
        this.cerveceria = cerveceria;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCerveza() {
        return cerveza;
    }

    public void setCerveza(String cerveza) {
        this.cerveza = cerveza;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**************************************************************************************/

    public DatosFeedback(){}

    public DatosFeedback(String cerveceria, Integer puntuacion, String cerveza, String comentario) {
        this.cerveceria = cerveceria;
        this.puntuacion = puntuacion;
        this.cerveza = cerveza;
        this.comentario = comentario;
    }

    public DatosFeedback(String cerveceria, Integer puntuacion, String cerveza, String comentario, Usuario usuario) {
        this.cerveceria = cerveceria;
        this.puntuacion = puntuacion;
        this.cerveza = cerveza;
        this.comentario = comentario;
        this.usuario = usuario;
    }
}

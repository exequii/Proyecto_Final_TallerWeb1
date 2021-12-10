package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import javax.persistence.*;

@Entity
public class DatosReview {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String cerveceria;
    private Integer puntuacion;
    private String cerveza;
    private String comentario;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

}

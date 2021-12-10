package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean leida;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Cerveceria cerveceria;


    public Notificacion() {
    }
    public Notificacion(Boolean leida) {
        this.leida = leida;
    }

    public Notificacion(Boolean leida, Usuario usuario, Cerveceria cerveceria) {
        this.leida = leida;
        this.usuario = usuario;
        this.cerveceria = cerveceria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cerveceria getCerveceria() {
        return cerveceria;
    }

    public void setCerveceria(Cerveceria cerveceria) {
        this.cerveceria = cerveceria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notificacion that = (Notificacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

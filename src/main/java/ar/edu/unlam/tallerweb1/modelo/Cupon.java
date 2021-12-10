package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @ManyToOne
    private Cerveceria cerveceriaDelCupon;

    @ManyToOne
    private Usuario usuarioDelCupon;

    /*********************************************************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cerveceria getCerveceriaDelCupon() {
        return cerveceriaDelCupon;
    }

    public void setCerveceriaDelCupon(Cerveceria cerveceriaDelCupon) {
        this.cerveceriaDelCupon = cerveceriaDelCupon;
    }

    public Usuario getUsuarioDelCupon() {
        return usuarioDelCupon;
    }

    public void setUsuarioDelCupon(Usuario usuarioDelCupon) {
        this.usuarioDelCupon = usuarioDelCupon;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /********************************************************************/

    public Cupon(){}

    public Cupon(String codigo, Cerveceria cerveceriaDelCupon, Usuario usuarioDelCupon) {
        this.codigo = codigo;
        this.cerveceriaDelCupon = cerveceriaDelCupon;
        this.usuarioDelCupon = usuarioDelCupon;
    }

    public Cupon(Long id, Cerveceria cerveceriaDelCupon, Usuario usuarioDelCupon) {
        this.id = id;
        this.cerveceriaDelCupon = cerveceriaDelCupon;
        this.usuarioDelCupon = usuarioDelCupon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cupon cupon = (Cupon) o;
        return Objects.equals(id, cupon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cupon{" +
                "id=" + id +
                ", cerveceriaDelCupon=" + cerveceriaDelCupon +
                ", usuarioDelCupon=" + usuarioDelCupon +
                '}';
    }
}

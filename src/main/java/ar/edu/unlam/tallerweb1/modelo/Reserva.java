package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidadPersonas;
    private Integer horario;
    private Boolean activa = true;

    @ManyToOne
    private Cerveceria cerveceria;

    @ManyToOne
    private Usuario usuario;

    /*****************************************************************/

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cantidadPersonas=" + cantidadPersonas +
                ", horario=" + horario +
                ", cerveceria=" + cerveceria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    /*****************************************************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    public Cerveceria getCerveceria() {
        return cerveceria;
    }

    public void setCerveceria(Cerveceria cerveceria) {
        this.cerveceria = cerveceria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    /******************************************************************/

    public Reserva(){}

    public Reserva(Integer cantidadPersonas, Integer horario, Cerveceria cerveceria){
        this.cantidadPersonas=cantidadPersonas;
        this.horario=horario;
        this.cerveceria=cerveceria;
    }

    public Reserva(Integer cantidadPersonas, Integer horario, Cerveceria cerveceria, Usuario usuario){
        this.cantidadPersonas=cantidadPersonas;
        this.horario=horario;
        this.cerveceria=cerveceria;
        this.usuario=usuario;
    }
}

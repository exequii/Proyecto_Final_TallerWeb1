package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;

import javax.persistence.ManyToOne;

public class DatosReserva {

    private Integer cantidadPersonas;
    private Integer horario;
    private String cerveceria;

    /*******************************************************/

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

    public String getCerveceria() {
        return cerveceria;
    }

    public void setCerveceria(String cerveceria) {
        this.cerveceria = cerveceria;
    }

    /***********************************************************/

    public DatosReserva(){}

    public DatosReserva(Integer cantidadPersonas, Integer horario, String cerveceria) {
        this.cantidadPersonas = cantidadPersonas;
        this.horario = horario;
        this.cerveceria = cerveceria;
    }
}

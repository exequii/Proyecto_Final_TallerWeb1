package ar.edu.unlam.tallerweb1.controladores;

public class DatosCoordenadas {

    private Double latitud;
    private Double longitud;
    private Double distancia;
    private Integer index;

    public DatosCoordenadas(){}

    public DatosCoordenadas(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public DatosCoordenadas(Double latitud, Double longitud, Double distancia) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.distancia = distancia;
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

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}

package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosCoordenadas;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;

import java.io.IOException;
import java.util.List;

public interface ServicioMapa {
    List<Double> buscarCoordenadasPorDireccion(String direccion);
    Double obtenerDistanciaEntreDosCoordenadas(DatosCoordenadas origen, DatosCoordenadas destino) throws IOException;
    List<DatosCoordenadas> obtenerListaDeCoordenadas(List<Cerveceria> cervecerias,Boolean validator);

}

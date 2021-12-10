package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosCoordenadas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioMapaTest {

    private ServicioMapa servicioMapa = new ServicioMapaImpl();

    @Test
    public void alEnviarDosCoordenadasObtengoDistanciaEntreLosPuntos() throws IOException {
        //GIVEN - Coordenadas de CORVALAN 1595,CABA
        DatosCoordenadas origen = new DatosCoordenadas(-34.652895, -58.491703);
        //GIVEN - Coordenadas de CORVALAN 2300,CABA
        DatosCoordenadas destino = new DatosCoordenadas(-34.648710, -58.497070);
        //WHEN
        Double distancia = whenEnvioLasCoordenadas(origen, destino);
        //THEN
        thenLaDistanciaEsDistintoANull(distancia);
    }

    @Test
    public void alEnviarUnaDireccionObtengoLasCoordenadas(){
        //Given
        String direccion = "Corvalan 1595, CABA";
        //WHEN
        List<Double> coordenadas = whenEnvioUnaDireccionObtengoCoordenadas(direccion);
        //THEN
        thenLasCoordenadasSeGeneranCorrectamente(coordenadas);
    }


    /*************************************** THEN **************************************/

    private void thenLaDistanciaEsDistintoANull(Double distancia) {
        assertThat(distancia).isNotNull();
        assertThat(distancia).isEqualTo(0.7);
    }

    private void thenLasCoordenadasSeGeneranCorrectamente(List<Double> coordenadas) {
        assertThat(coordenadas.get(0)).isNotNull();
        assertThat(coordenadas.get(1)).isNotNull();
        assertThat(coordenadas.get(0)).isEqualTo(-34.6529091);
        assertThat(coordenadas.get(1)).isEqualTo(-58.4917724);
    }

    /************************************** WHEN **************************************/

    private Double whenEnvioLasCoordenadas(DatosCoordenadas origen, DatosCoordenadas destino) throws IOException {
        Double distancia = servicioMapa.obtenerDistanciaEntreDosCoordenadas(origen,destino);
        return distancia;
    }

    private List<Double> whenEnvioUnaDireccionObtengoCoordenadas(String direccion) {
        List<Double> coordenadas = servicioMapa.buscarCoordenadasPorDireccion(direccion);
        return coordenadas;
    }

}

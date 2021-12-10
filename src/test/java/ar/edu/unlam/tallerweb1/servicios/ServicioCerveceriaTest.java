package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Feedback;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCerveceria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFeedback;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
public class ServicioCerveceriaTest {

    private RepositorioCerveceria repositorioCerveceria = mock(RepositorioCerveceria.class);
    private RepositorioFeedback repositorioFeedback = mock(RepositorioFeedback.class);
    private ServicioMapa servicioMapa = mock(ServicioMapa.class);
    private ServicioCerveceria servicioCerveceria = new ServicioCerveceriaImpl(repositorioCerveceria,repositorioFeedback,servicioMapa);

    private Cerveceria cerveceria1 = new Cerveceria("Antares", "Calle 123");
    private Cerveceria cerveceria2 = new Cerveceria("La birra","123");
    private Usuario usuario = mock(Usuario.class);
    private Feedback feedback = new Feedback(5,"",cerveceria1,"",usuario);
    private Feedback feedback1 = new Feedback(3,"",cerveceria1,"",usuario);
    private Feedback feedback2 = new Feedback(2,"",cerveceria1,"",usuario);

    @Test
    public void puedoTraerTodasLasCervecerias() {
        givenTengoVariasCerveceriasGuardadas();
        whenBuscoLasCervecerias();
        thenTraeLasCervecerias();
    }
    @Test
    public void puedoBuscarUnaCerveceria(){
        givenTengoVariasCerveceriasGuardadas();
        Cerveceria cerveceria = whenBuscoLaCerveceria();
        thenEncuentraLaCerveceria(cerveceria);
    }
    @Test
    public void puedoVerPromedioDePuntuacionDeCerveceria() {
        givenTengoVariosFeedbacksDeUnaCerveceria();
        double promedioEsperado = whenBuscoElPromedioDeLaCerveceria();
        thenObtengoElPromedio(promedioEsperado);
    }

    @Test
    public void puedoObtenerCerveceriaMejorPunteada(){
        List<Cerveceria> cervecerias = givenTengoVariasCervecerias();
        Cerveceria cerveceriaMejorPunteada = whenSeteoLaCerveceriaMejorPunteada(cervecerias);
        thenLaCerveceriaMejorPunteadaCoincide(cerveceriaMejorPunteada, 5.0);
    }


    /******************************* GIVEN ******************************/
    private void givenTengoVariosFeedbacksDeUnaCerveceria() {
        repositorioFeedback.guardar(feedback);
        repositorioFeedback.guardar(feedback1);
        repositorioFeedback.guardar(feedback2);
        servicioCerveceria.guardarCerveceria(cerveceria1);
    }
    private void givenTengoVariasCerveceriasGuardadas() {
        servicioCerveceria.guardarCerveceria(cerveceria1);
        servicioCerveceria.guardarCerveceria(cerveceria2);

    }

    private List<Cerveceria> givenTengoVariasCervecerias() {
        Cerveceria antares = new Cerveceria("Antares","Corvalan 1595,CABA");
        Cerveceria cervelar = new Cerveceria("Cervelar","Tinogasta 5444, CABA");
        Cerveceria labirra = new Cerveceria("labirra","Tinogasta 4444, CABA");
        List<Cerveceria> cervecerias = new LinkedList<>();
        antares.setPromedioPuntuacion(4.0);
        cervelar.setPromedioPuntuacion(5.0);
        labirra.setPromedioPuntuacion(3.0);
        cervecerias.add(antares);
        cervecerias.add(cervelar);
        cervecerias.add(labirra);
        return cervecerias;
    }

    /******************************* WHEN ******************************/
    private double whenBuscoElPromedioDeLaCerveceria() {
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);
        feedbacks.add(feedback2);
        feedbacks.add(feedback1);
        when(repositorioFeedback.buscarFeedbacksPorCerveceria(cerveceria1.getNombre())).thenReturn(feedbacks);
        double promedio = 10/(double)feedbacks.size();
        return Math.round(promedio*100.0)/100.0;
    }
    private Cerveceria whenBuscoLaCerveceria() {
        return servicioCerveceria.buscarCerveceriaPorNombre("Antares");
    }

    private List<Cerveceria> whenBuscoLasCervecerias() {
        return servicioCerveceria.buscarCervecerias();
    }

    private Cerveceria whenSeteoLaCerveceriaMejorPunteada(List<Cerveceria> cervecerias) {
        List<Cerveceria> cerveceriasConMejorPunteada = servicioCerveceria.setearCerveceriaMejorPuntuada(cervecerias);
        for(Cerveceria cerceriaFiltrada : cerveceriasConMejorPunteada){
            if(cerceriaFiltrada.getMejorPunteada() == true){
                return cerceriaFiltrada;
            }
        }
        return null;
    }


    /******************************* THEN ******************************/
    private void thenObtengoElPromedio(double promedioEsperado) {
        assertThat(servicioCerveceria.obtenerElPromedioDePuntuacionPorNombre(cerveceria1.getNombre())).isEqualTo(promedioEsperado);
    }
    private void thenEncuentraLaCerveceria(Cerveceria cerveceria) {
        verify(repositorioCerveceria, times(1)).buscarCerveceriaPorNombre("Antares");
    }
    private void thenTraeLasCervecerias() {
        verify(repositorioCerveceria,times(1)).buscarCervecerias();
    }

    private void thenLaCerveceriaMejorPunteadaCoincide(Cerveceria mejorPunteada, Double puntuacionEsperada) {
        assertThat(mejorPunteada.getPromedioPuntuacion().equals(puntuacionEsperada));
    }

}

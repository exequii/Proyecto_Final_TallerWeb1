package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MapaControllerTest {
    MapaController mapaController = new MapaController();
    Cerveceria cerveceria = new Cerveceria("Pentos","Presidente Perón 430");
    ModelAndView model = new ModelAndView();



    @Test
    public void testObtengoLatitudYLongitudDeUnaCerveceria() throws IOException {
        givenTengoCerveceriaConDireccion();
        whenObtengoLocacion();
        double latitudEsperada = -34.638549;
        double longitudEsperada = -58.5715021;
        thenLaLocacionEsCorrecta(latitudEsperada,longitudEsperada);
    }

    private void thenLaLocacionEsCorrecta(double latitudEsperada, double longitudEsperada) {
        assertThat(model.getModel().get("latitud")).isEqualTo(latitudEsperada);
        assertThat(model.getModel().get("longitud")).isEqualTo(longitudEsperada);

    }

    private void whenObtengoLocacion() {
        model = mapaController.mapa(cerveceria.getDireccion());
    }

    private void givenTengoCerveceriaConDireccion() {
    }
}

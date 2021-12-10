package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MisDatosControllerTest {
    private ServicioFeedback servicioFeedback = mock(ServicioFeedback.class);
    private ServicioReserva servicioReserva = mock(ServicioReserva.class);
    private ServicioCerveceria servicioCerveceria = mock(ServicioCerveceria.class);
    private ServicioLogin servicioLogin = mock(ServicioLogin.class);
    private ServicioMapa servicioMapa = mock(ServicioMapa.class);
    private ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);

    private MisDatosController misDatosController = new MisDatosController(servicioFeedback,servicioReserva,servicioCerveceria,servicioLogin,servicioMapa, servicioUsuario);
    private ModelAndView model;
    HttpServletRequest request = mock(HttpServletRequest.class);
    List<Cerveceria> cervecerias = mock(List.class);

    @Test
    public void puedoAccederAMisDatos() {
        givenExistenMisDatos();
        ModelAndView model = whenAccedoALosDatos();
        thenMuestraLosDatosDelUsuario(model);
    }

    private void givenExistenMisDatos() {
        HttpSession session = mock(HttpSession.class);
        Usuario usuario = mock(Usuario.class);
        when(usuario.getEmail()).thenReturn("usuario@usuario.com");
        when(session.getAttribute("usuario")).thenReturn(usuario);
        when(request.getSession()).thenReturn(session);
        when(cervecerias.size()).thenReturn(1);
        when(servicioCerveceria.buscarCervecerias()).thenReturn(cervecerias);
    }

    private void thenMuestraLosDatosDelUsuario(ModelAndView model) {
        assertThat(model.getViewName()).isEqualTo("misDatos");
        assertThat(model.getModel().get("usuario")).isEqualTo("usuario@usuario.com");
    }

    private ModelAndView whenAccedoALosDatos() {
        return model = misDatosController.misDatos((request));
    }
}

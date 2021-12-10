package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioLoginTest {

    public static final String EMAIL = "seba@seba.com";
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioMapa servicioMapa = mock(ServicioMapa.class);
    private ServicioLogin servicioLogin = new ServicioLoginImpl(repositorioUsuario,servicioMapa);

    @Test(expected = Exception.class)
    public void siMeRegistroConUsuarioExistenteDaError() throws Exception {
        givenUsuarioYaExiste(EMAIL);
        whenResgistro(EMAIL);
        thenElUsuarioNoSeGuarda();
    }

    @Test
    public void deberiaRegistrarUsuarioSiNoExiste() throws Exception {
        givenUsuarioNoExiste(EMAIL);
        Usuario creado = whenResgistro(EMAIL);
        thenElRegistroEsExitoso(creado);
    }

    /********************************** GIVEN *******************************************/

    private void givenUsuarioNoExiste(String email) {
        when(repositorioUsuario.buscar(email)).thenReturn(null);
    }

    private void givenUsuarioYaExiste(String email) {
        when(repositorioUsuario.buscar(email)).thenReturn(new Usuario());
    }


    /********************************** WHEN *********************************************/

    private Usuario whenResgistro(String email) throws Exception {
        return servicioLogin.registrar(email, "67447");
    }

    /********************************** THEN *********************************************/

    private void thenElUsuarioNoSeGuarda() {
        verify(repositorioUsuario, never()).guardar(any());
    }

    private void thenElRegistroEsExitoso(Usuario creado) {
        assertThat(creado).isNotNull();
        assertThat(creado.getEmail()).isEqualTo(EMAIL);
        verify(repositorioUsuario, times(1)).guardar(any());
    }
}

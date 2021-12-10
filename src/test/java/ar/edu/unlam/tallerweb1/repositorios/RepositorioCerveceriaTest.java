package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Cerveza;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class RepositorioCerveceriaTest extends SpringTest {
    @Autowired
    private RepositorioCerveceria repositorioCerveceria;

    private Cerveceria cerveceria = new Cerveceria("Antares", "Calle 123");

    @Test @Rollback @Transactional
    public void puedoGuardarUnaCerveceria() {
        givenTengoUnaCerveceria();
        List<Cerveceria> cervecerias = whenBuscoLaCerveceria();
        thenEncuentro(cervecerias.size(),1);
    }



    /******************************* THEN ******************************/

    private void thenEncuentro(int valorObtenido, int valorEsperado) {
        assertThat(valorObtenido).isEqualTo(valorEsperado);
    }
    /******************************* WHEN ******************************/

    private List<Cerveceria> whenBuscoLaCerveceria() {
        return repositorioCerveceria.buscarCervecerias();
    }


    /******************************* GIVEN ******************************/

    private void givenTengoUnaCerveceria() {
        session().save(cerveceria);
    }
    private void givenTengoUnaCerveceriaConCervezas() {
        cerveceria.addCerveza(new Cerveza());
        cerveceria.addCerveza(new Cerveza());
        session().save(cerveceria);
    }
}

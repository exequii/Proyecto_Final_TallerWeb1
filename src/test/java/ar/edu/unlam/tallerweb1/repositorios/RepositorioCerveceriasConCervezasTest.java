
package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RepositorioCerveceriasConCervezasTest extends SpringTest {


    private List<Cerveza> cervezas = new LinkedList<>();
    private final Cerveceria CERVECERIA
            = new Cerveceria("Antares","Palermo", cervezas);

    @Autowired
    private RepositorioCerveceria repositorioCerveceria;


    @Test
    @Rollback @Transactional
    public void unaCerveceriaTieneMuchasCervezas(){
        givenTengoUnaCerveceriaConCervezas(3, CERVECERIA);
        List<Cerveceria> cervecerias = whenBuscoCerveceriasQueTenganUnaCervezaEspecifica("Cerveza1");
        thenEncuentroCerveceria(cervecerias,1);

    }


    /******************************************* GIVEN ********************************************/

    private void givenTengoUnaCerveceriaConCervezas(int cantidadCervezas, Cerveceria CERVECERIA) {
        for(int i = 0; i < cantidadCervezas; i++){
            Cerveza cerveza = new Cerveza();
            cerveza.setNombre("Cerveza" + i + "");
            cerveza.setMarca("Marca" + i + "");
            cerveza.setTipo(TipoCerveza.RUBIA);
            cerveza.setCerveceria(CERVECERIA);
            CERVECERIA.addCerveza(cerveza);
        }
        session().save(CERVECERIA);
    }

    /******************************************* WHEN *********************************************/

    private List<Cerveceria> whenBuscoCerveceriasQueTenganUnaCervezaEspecifica(String nombreCerveza) {
        return repositorioCerveceria.buscarCerveceriaPorNombreDeCerveza(nombreCerveza);
    }

    /******************************************* THEN *********************************************/

    private void thenEncuentroCerveceria(List<Cerveceria> cervecerias, int cantidadCervecerias) {
        assertThat(cervecerias).hasSize(cantidadCervecerias);
    }

}

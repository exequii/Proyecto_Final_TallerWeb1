package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosCoordenadas;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCerveceria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFeedback;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service("servicioCerveceria")
@Transactional
public class ServicioCerveceriaImpl implements ServicioCerveceria{

    private ServicioMapa servicioMapa;
    private RepositorioCerveceria repositorioCerveceria;
    private RepositorioFeedback repositorioFeedback;
    private RepositorioReserva repositorioReserva;

    private List<Reserva> reservasCerveceria = new LinkedList<>();

    @Autowired
    public ServicioCerveceriaImpl(RepositorioCerveceria repositorioCerveceria,RepositorioFeedback repositorioFeedback, ServicioMapa servicioMapa) {
        this.repositorioCerveceria = repositorioCerveceria;
        this.repositorioFeedback = repositorioFeedback;
        this.servicioMapa = servicioMapa;
    }

    @Override
    public Double obtenerElPromedioDePuntuacionPorNombre(String nombre) {
        List<Feedback> feedbacks = repositorioFeedback.buscarFeedbacksPorCerveceria(nombre);
        Double promedio = 0.0;
        Integer cantidadFeedbacks = feedbacks.size();
        for(Feedback x : feedbacks){
            promedio = promedio + x.getPuntuacion();
        }
        promedio = promedio / cantidadFeedbacks;

        return Math.round(promedio*100.0)/100.0;
    }

    @Override
    public List<Cerveceria> setearCerveceriaMejorPuntuada(List<Cerveceria> cervecerias) {
        Double mayorPromedio = 0.0;
        Cerveceria mejorPuntuada = new Cerveceria();
        for(Cerveceria cerveceriaAPuntuar : cervecerias){
            Double promedioo = obtenerElPromedioDePuntuacionPorNombre(cerveceriaAPuntuar.getNombre());
            cerveceriaAPuntuar.setPromedioPuntuacion(promedioo);
            if(cerveceriaAPuntuar.getPromedioPuntuacion() >= mayorPromedio){
                cerveceriaAPuntuar.setMejorPunteada(true);
                mayorPromedio = cerveceriaAPuntuar.getPromedioPuntuacion();
                mejorPuntuada.setMejorPunteada(false);
                mejorPuntuada = cerveceriaAPuntuar;
            }
        }
        return cervecerias;
    }

    @Override
    public List<Cerveceria> filtrarCerveceriaPorCercania(Usuario usuario,String distancia) throws IOException {
        List<Cerveceria> cervecerias = buscarCervecerias();
        DatosCoordenadas origen = new DatosCoordenadas(usuario.getLatitud(),usuario.getLongitud());
        DatosCoordenadas destino = new DatosCoordenadas();
        Double distanciaMaxima = Double.parseDouble(distancia);
        for (Cerveceria cerveeriaAFiltrar : cervecerias){
            destino.setLatitud(cerveeriaAFiltrar.getLatitud());
            destino.setLongitud(cerveeriaAFiltrar.getLongitud());
            Double distanciaEntreUsuarioCerveceria = servicioMapa.obtenerDistanciaEntreDosCoordenadas(origen, destino);
            if(distanciaEntreUsuarioCerveceria >= distanciaMaxima){
                //cervecerias.remove(cerveeriaAFiltrar);
                cerveeriaAFiltrar.setMostrar(false);
            }else{
                cerveeriaAFiltrar.setMostrar(true);
            }
        }
        return cervecerias;
    }

//List<Double> promedios = new ArrayList<>();
//Double promedio = x.getPromedioPuntuacion();
//promedios.add(promedio);
//}
//Double mayorPromedio = Collections.max(promedios);
//for(Cerveceria x : cervecerias){
//Double promedio = x.getPromedioPuntuacion();
//if(promedio >= mayorPromedio && promedio != 0.0){
//x.setMejorPunteada(true);
//}


    @Override
    public void guardarCerveceria(Cerveceria cerveceria) {
        Cerveceria nuevo = new Cerveceria();
        nuevo.setNombre(cerveceria.getNombre());
        nuevo.setDireccion(cerveceria.getDireccion());
        repositorioCerveceria.guardar(nuevo);
    }

    @Override
    public void guardarCerveceria(String nombre, String direccion, List<Cerveza> cervezas) {
        Cerveceria nuevo = new Cerveceria();
        nuevo.setNombre(nombre);
        nuevo.setDireccion(direccion);
        nuevo.setCervezas(cervezas);
        repositorioCerveceria.guardar(nuevo);
    }

    @Override
    public void guardarCerveceria(String nombre, String direccion, String barrio, Usuario propietario) {
        Cerveceria nuevo = new Cerveceria();
        nuevo.setNombre(nombre);
        nuevo.setDireccion(direccion);
        nuevo.setPropietario(propietario);
        nuevo.setBarrio(barrio);
        List<Double> coordenadasCerveceria = servicioMapa.buscarCoordenadasPorDireccion(nuevo.getDireccion());
        nuevo.setLatitud(coordenadasCerveceria.get(0));
        nuevo.setLongitud(coordenadasCerveceria.get(1));
        repositorioCerveceria.guardar(nuevo);
    }

    @Override
    public void eliminarCerveceria(Long id) {
        Cerveceria buscada = repositorioCerveceria.buscarCerveceriaPorId(id);
        buscada.setPropietario(null);
        buscada.setReservas(null);
        repositorioCerveceria.eliminar(buscada);
    }

    @Override
    public void actualizar(Cerveceria cerveceria) {
        //cerveceria.setReservas(reservasCerveceria);
        cerveceria.setPropietario(null);
        cerveceria.setReservas(null);
        repositorioCerveceria.actualizar(cerveceria);
    }

    @Override
    public List<Cerveceria> buscarCervecerias() {
        return repositorioCerveceria.buscarCervecerias();
    }

    @Override
    public List<Cerveceria> buscarCerveceriasPorUsuario(String email) {
        return repositorioCerveceria.buscarCerveceriaPorUsuario(email);
    }

    @Override
    public Cerveceria buscarCerveceriaPorId(Long id) {
        return repositorioCerveceria.buscarCerveceriaPorId(id);
    }

    @Override
    public Cerveceria buscarCerveceriaPorNombre(String nombre) {
        return repositorioCerveceria.buscarCerveceriaPorNombre(nombre);
    }

    @Override
    public List<Cerveceria> buscarCerveceriaPorBarrio(String barrio) {
        return repositorioCerveceria.buscarCerveceriasPorBarrio(barrio);
    }



}

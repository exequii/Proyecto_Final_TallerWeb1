package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MisDatosController {
    private ServicioFeedback servicioFeedback;
    private ServicioReserva servicioReserva;
    private ServicioCerveceria servicioCerveceria;
    private ServicioLogin servicioLogin;
    private ServicioMapa servicioMapa;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public MisDatosController(ServicioFeedback servicioFeedback, ServicioReserva servicioReserva, ServicioCerveceria servicioCerveceria, ServicioLogin servicioLogin, ServicioMapa servicioMapa, ServicioUsuario servicioUsuario) {
        this.servicioFeedback = servicioFeedback;
        this.servicioReserva = servicioReserva;
        this.servicioCerveceria = servicioCerveceria;
        this.servicioLogin = servicioLogin;
        this.servicioMapa = servicioMapa;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(path = "/mis-datos")
    public ModelAndView misDatos( HttpServletRequest request) {
        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();

        DatosReview datos = new DatosReview();
        model.put("datos", datos);

        Cerveceria cerveceria = new Cerveceria();
        model.put("cerveceria", cerveceria);

        Reserva reserva = new Reserva();
        model.put("reserva", reserva);

        Usuario usuarioRecibido = (Usuario) session.getAttribute("usuario");
        model.put("usuario", usuarioRecibido.getEmail());

        String barrio = (String) session.getAttribute("barrio");
        model.put("barrio", barrio);

        model.put("usuarioLat", usuarioRecibido.getLatitud()+"");
        model.put("usuarioLong", usuarioRecibido.getLongitud()+"");

        if (usuarioRecibido.getRol() == "admin") {
            model.put("admin", "admin");
        }

        if(barrio != null) {
            List<Cerveceria> cerveceriasPorBarrio = servicioCerveceria.buscarCerveceriaPorBarrio(barrio);
            List<DatosCoordenadas> coordenadas = servicioMapa.obtenerListaDeCoordenadas(cerveceriasPorBarrio,true);
            int cantidadCervecerias = coordenadas.size();
            String latitudCerveceria[] = new String[cantidadCervecerias];
            String longitudCerveceria[] = new String[cantidadCervecerias];
            int index = 0;
            for (DatosCoordenadas coordenada : coordenadas){
                latitudCerveceria[index] = coordenada.getLatitud()+"";
                longitudCerveceria[index] = coordenada.getLongitud()+"";
                index++;
            }
            model.put("cantidadCervecerias",cantidadCervecerias);
            model.put("latitudCerveceria",latitudCerveceria);
            model.put("longitudCerveceria",longitudCerveceria);
        }
        
            //FEEDBACKS
            List<Feedback> feedbacks = servicioFeedback.buscarFeedbacksPorUsuario(usuarioRecibido.getEmail());
            List<DatosReview> reviews = servicioFeedback.generarListaReviews(feedbacks);
            if(reviews.size() != 0){
                model.put("datosReview", reviews);
                //model.addAttribute(feedbacks);
            }else{
                model.put("msg", "No se encontraron reviews");
            }

            //RESERVAS
            List<Reserva> reservas = servicioReserva.consultarReservasPorUsuario(usuarioRecibido.getEmail());
            List<Reserva> reservasAMostrar = servicioReserva.filtrarReservasActivas(reservas);
            if(reservasAMostrar.size() != 0 ) {
                model.put("misReservas", reservasAMostrar);
            }else{
                model.put("msg1","Aun no tienes reservas realizadas");}

            //CERVECERIAS
            List<Cerveceria> cervecerias = servicioCerveceria.buscarCervecerias();
            if(cervecerias.size() != 0 ){
            model.put("misCervecerias", cervecerias);
           }else {
                model.put("msg2", "Aun no tienes cervecerias creadas");
            }

            //CUPONES
            List<Cupon> cupones = servicioUsuario.buscarCuponesPorUsuario(usuarioRecibido.getEmail());
            if (cupones.size()!=0) {
                model.put("cupones",cupones);
            } else {
                model.put("msg4","No tienes ningun cupón de descuento");
            }

            //NOTIFICACIONES
            List<Notificacion> notificaciones = servicioUsuario.buscarNotificaciones(usuarioRecibido.getEmail());
            model.put("notificaciones",notificaciones.size());
             for (Notificacion notificacion : notificaciones) {
                 if (notificacion.getUsuario().getEmail() == usuarioRecibido.getEmail()) {
                     servicioUsuario.actualizarEstadoNotificacion(notificacion.getId());
                 }
             }



        return new ModelAndView("misDatos", model);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/eliminar-review")
    public ModelAndView eliminarReview(@ModelAttribute("datos") DatosReview datos) {

        ModelMap model = new ModelMap();
        Integer id = Math.toIntExact(datos.getId());

        try {
            servicioFeedback.eliminarFeedback(id);
        } catch (Exception e) {
            model.put("msg", "No se pudo eliminar la review.");
            DatosBuscador datos1 = new DatosBuscador();
            model.put("buscador", datos1);
            return new ModelAndView("home", model);
        }
        DatosBuscador datosBuscador = new DatosBuscador();
        model.put("buscador", datosBuscador);
        model.put("msg", "Review eliminada correctamente");
        return new ModelAndView("home", model);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/eliminar-reserva")
    public ModelAndView eliminarReserva(@ModelAttribute("reserva") Reserva reserva, HttpServletRequest request) {

        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario) session.getAttribute("usuario");
        Cerveceria cerveceriaEncontrada = servicioCerveceria.buscarCerveceriaPorNombre(reserva.getCerveceria().getNombre());
        try {
            servicioReserva.eliminarReserva(reserva.getId(), usuarioRecibido, cerveceriaEncontrada);
            Integer a=1;
        } catch (Exception e) {
            DatosBuscador datos2 = new DatosBuscador();
            model.put("buscador", datos2);
            model.put("msg", "No se pudo eliminar la reserva.");
            return new ModelAndView("home", model);
        }
        DatosBuscador datosBuscador = new DatosBuscador();
        model.put("buscador", datosBuscador);
        model.put("msg", "Reserva eliminada correctamente");
        return new ModelAndView("home", model);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/eliminar-cerveceria")
    public ModelAndView eliminarCerveceria(@ModelAttribute("cerveceria") Cerveceria cerveceria) {

        ModelMap model = new ModelMap();
        try {
            servicioCerveceria.eliminarCerveceria(cerveceria.getId());
        } catch (Exception e) {
            DatosBuscador datos = new DatosBuscador();
            model.put("buscador", datos);
            model.put("msg", "No se pudo eliminar la cerveceria.");
            return new ModelAndView("home", model);
        }

        model.put("msg", "Cerveceria eliminada correctamente");
        DatosBuscador datos = new DatosBuscador();
        model.put("buscador", datos);
        return new ModelAndView("home", model);
    }
}

package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Disponibilidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCerveceria;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
//import com.sun.deploy.cache.BaseLocalApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReservaController extends HttpServlet {

    private ServicioReserva servicioReserva;
    private ServicioCerveceria servicioCerveceria;
    private Map<Disponibilidad,String> respuestaReserva;

    @Autowired
    public ReservaController(ServicioReserva servicioReserva, ServicioCerveceria servicioCerveceria){
        this.servicioReserva = servicioReserva;
        this.servicioCerveceria = servicioCerveceria;
        this.respuestaReserva = new HashMap<>();
        respuestaReserva.put(Disponibilidad.EN_HORARIO,"Reserva realizada Correctamente");
        respuestaReserva.put(Disponibilidad.HORA_ANTERIOR,"No hay lugares disponibles en el horario solicitado. Pero tenemos lugar para la hora anterior.");
        respuestaReserva.put(Disponibilidad.HORA_POSTERIOR,"No hay lugares disponibles en el horario solicitado. Pero tenemos lugar para la hora posterior.");
        respuestaReserva.put(Disponibilidad.NO_DISPONIBLE,"No hay lugares disponibles. Tampoco encontramos horarios proximos a su reserva con disponibilidad.");
    }

    @RequestMapping(method = RequestMethod.GET,path ="/reserva-formulario")
    public ModelAndView irAFormReservas (HttpServletRequest request){
        ModelMap model = new ModelMap();
        DatosReserva reserva = new DatosReserva();
        model.put("reserva", reserva);
        List<Cerveceria> cervecerias = servicioCerveceria.buscarCervecerias();
        model.put("cervecerias", cervecerias);

        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");
        if(usuarioRecibido!=null){
            model.put("logeado","true");
        }

        return new ModelAndView("reserva-formulario", model);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/crear-reserva")
    public ModelAndView registrarReserva(@ModelAttribute("reserva") DatosReserva reserva, HttpServletRequest request){

        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");

        Disponibilidad disponibilidad = null;

        disponibilidad = servicioReserva.guardarReserva(reserva.getCerveceria(),reserva.getCantidadPersonas(),reserva.getHorario(),usuarioRecibido);
        model.put("msg", respuestaReserva.get(disponibilidad));

        if(respuestaReserva.get(disponibilidad).equals(respuestaReserva.get(Disponibilidad.EN_HORARIO)) == false){
            List<Cerveceria> cervecerias = servicioCerveceria.buscarCervecerias();
            model.put("cervecerias", cervecerias);
            return new ModelAndView("reserva-formulario", model);
        }

        DatosBuscador datos = new DatosBuscador();
        model.put("cerveceria", reserva.getCerveceria());
        model.put("cantidad", reserva.getCantidadPersonas());
        model.put("horario", reserva.getHorario());
        model.put("buscador", datos);
        return new ModelAndView("home", model);
    }











    public ModelAndView registrarReservaParaTest(@ModelAttribute("reserva") DatosReserva reserva) {

        ModelMap model = new ModelMap();
        Usuario usuario = new Usuario();


        try {
            servicioReserva.guardarReserva(reserva.getCerveceria(),reserva.getCantidadPersonas(),reserva.getHorario(),usuario);
        } catch (Exception e) {
            model.put("msg", e.getMessage());
            return new ModelAndView("reserva-formulario", model);
        }

        model.put("msg", "Reserva guardada correctamente");

        model.put("cerveceria", reserva.getCerveceria());
        model.put("cantidad", reserva.getCantidadPersonas());
        model.put("horario", reserva.getHorario());

        return new ModelAndView("home", model);
    }


}

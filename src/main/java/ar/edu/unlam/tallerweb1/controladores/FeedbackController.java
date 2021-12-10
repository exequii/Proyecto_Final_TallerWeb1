package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCerveceriaException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCervezaException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaComentarioException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaPuntuacionException;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCerveceria;
import ar.edu.unlam.tallerweb1.servicios.ServicioFeedback;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FeedbackController extends HttpServlet {

    private ServicioFeedback servicioFeedback;
    private ServicioCerveceria servicioCerveceria;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public FeedbackController(ServicioFeedback servicioFeedback, ServicioCerveceria servicioCerveceria,ServicioUsuario servicioUsuario){
        this.servicioFeedback = servicioFeedback;
        this.servicioCerveceria = servicioCerveceria;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(method = RequestMethod.GET,path ="/feedback")
    public ModelAndView irAFeedback (HttpServletRequest request){
        ModelMap model = new ModelMap();
        DatosFeedback feedback = new DatosFeedback();
        model.put("feedback", feedback);
        List<Cerveceria> cervecerias = servicioCerveceria.buscarCervecerias();
        model.put("cervecerias", cervecerias);


        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");
        if(usuarioRecibido!=null){
            model.put("logeado","true");
        }

        return new ModelAndView("feedback", model);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/dar-feedback")
    public ModelAndView registrarFeedback(@ModelAttribute("feedback") DatosFeedback feedback, HttpServletRequest request) {

        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");
        Cerveceria cerveceria = servicioCerveceria.buscarCerveceriaPorNombre(feedback.getCerveceria());

            try {
                servicioFeedback.guardarFeedback(cerveceria,feedback.getPuntuacion(),feedback.getCerveza(),feedback.getComentario(),usuarioRecibido);
            } catch (Exception e) {
                e.printStackTrace();
                model.put("msg", "No se pudo enviar el feedback");
                DatosBuscador datos = new DatosBuscador();
                model.put("buscador", datos);
                return new ModelAndView("home", model);
            }

            model.put("msg", "Feedback guardado correctamente");
            DatosBuscador datosBuscador = new DatosBuscador();
            model.put("buscador", datosBuscador);
            model.put("puntuacion", feedback.getPuntuacion());
            model.put("cerveceria", feedback.getCerveceria());
            model.put("cerveza", feedback.getCerveza());
            model.put("comentario", feedback.getComentario());

//        List<Notificacion> notificaciones = new LinkedList<>();
//        Notificacion notificacion = new Notificacion(false);
//        notificaciones.add(notificacion);
//        usuarioRecibido.setNotificaciones(notificaciones);


        Notificacion notificacion = new Notificacion(false,cerveceria.getPropietario(),cerveceria);
        servicioUsuario.guardarNotificacion(notificacion);


            return new ModelAndView("home", model);
    }


    public ModelAndView registrarFeedbackParaTest(@ModelAttribute("feedback") DatosFeedback feedback) {

        ModelMap model = new ModelMap();

        try {
            servicioFeedback.guardarFeedback(feedback);
        } catch (FaltaCerveceriaException e) {
            model.put("msg", "Falta cerveceria");
            return new ModelAndView("feedback", model);
        } catch (FaltaCervezaException e) {
            model.put("msg", "Falta cerveza");
            return new ModelAndView("feedback", model);
        }catch (FaltaPuntuacionException e) {
            model.put("msg", "Falta puntuacion");
            return new ModelAndView("feedback", model);
        }catch (FaltaComentarioException e) {
            model.put("msg", "Falta comentario");
            return new ModelAndView("feedback", model);
        }

        model.put("msg", "Feedback guardado correctamente");

        model.put("puntuacion", feedback.getPuntuacion());
        model.put("cerveceria", feedback.getCerveceria());
        model.put("cerveza", feedback.getCerveza());
        model.put("comentario", feedback.getComentario());

        return new ModelAndView("home", model);
    }

}

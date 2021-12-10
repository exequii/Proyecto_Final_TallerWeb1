package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCerveceria;
import ar.edu.unlam.tallerweb1.servicios.ServicioFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CerveceriaController extends HttpServlet {

    private ServicioCerveceria servicioCerveceria;


    @Autowired
    public CerveceriaController(ServicioCerveceria servicioCerveceria){
        this.servicioCerveceria = servicioCerveceria;
    }

    @RequestMapping(method = RequestMethod.GET,path ="/cerveceria")
    public ModelAndView irAFormCerveceria (HttpServletRequest request){
        ModelMap model = new ModelMap();
        DatosCerveceria cerveceria = new DatosCerveceria();
        model.put("cerveceria", cerveceria);

        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");

        if(usuarioRecibido!=null){
            model.put("logeado","true");
            if(usuarioRecibido.getRol().equals("admin")){
                //Agrego esto para que solo cuentas con rol admin puedan generar cervecerias.
                //Aun no fue implementado en los usuarios la asignaccion de rol
                model.put("admin","true");
            }
        }

        return new ModelAndView("cerveceria", model);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/crear-cerveceria")
    public ModelAndView crearCerveceria(@ModelAttribute("cerveceria") DatosCerveceria cerveceria, HttpServletRequest request) {

        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");

        try {
            servicioCerveceria.guardarCerveceria(cerveceria.getNombre(),cerveceria.getDireccion(),cerveceria.getBarrio(),usuarioRecibido);
        } catch (Exception e) {
            model.put("msg", "No se pudo registrar la cerveceria");
            DatosBuscador datos = new DatosBuscador();
            model.put("buscador", datos);
            return new ModelAndView("home", model);
        }

        model.put("msg", "La cerveceria se registro correctamente");
        DatosBuscador datos = new DatosBuscador();
        model.put("buscador", datos);
        return new ModelAndView("home", model);
    }


}

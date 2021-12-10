package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCerveceria;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class InfoUsuarioController {

    private ServicioLogin servicioLogin;

    @Autowired
    public InfoUsuarioController(ServicioLogin servicioLogin){
        this.servicioLogin = servicioLogin;

    }

    @RequestMapping(path = "/infoUsuario")
    public ModelAndView infoUsuario(HttpServletRequest request){
        ModelMap model = new ModelMap();
        DatosRegistro datos = new DatosRegistro();
        model.put("datos", datos);
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");

        if(usuarioRecibido!=null){
            model.put("logeado","true");
        }

        model.put("barrio",usuarioRecibido.getBarrio());

        return new ModelAndView("infoUsuario",model);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/dar-barrio")
    public ModelAndView actualizarPerfil(@ModelAttribute("datos") DatosRegistro datos, HttpServletRequest request) {

        ModelMap model = new ModelMap();

        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");

                try {
                    Usuario usuario = servicioLogin.actualizarPerfil(datos.getBarrio(),datos.getDireccion(), usuarioRecibido);
                    request.getSession().setAttribute("usuario", usuario);
                } catch (Exception e) {
                    model.put("msg", "Ha ocurrido un error al actualizar el perfil");
                    return new ModelAndView("infoUsuario", model);
                }

                request.getSession().setAttribute("barrio", datos.getBarrio());
                model.put("msg", "Se ha actualizado su barrio correctamente.");
                return new ModelAndView("infoUsuario", model);

    }
}

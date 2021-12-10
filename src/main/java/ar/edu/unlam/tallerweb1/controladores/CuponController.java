package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Cupon;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCerveceria;
import ar.edu.unlam.tallerweb1.servicios.ServicioFeedback;
import ar.edu.unlam.tallerweb1.servicios.ServicioMapa;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
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
import java.util.List;
import java.util.Random;

@Controller
public class CuponController extends HttpServlet {

    private ServicioCerveceria servicioCerveceria;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public CuponController(ServicioCerveceria servicioCerveceria, ServicioUsuario servicioUsuario){
        this.servicioCerveceria = servicioCerveceria;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(path = "/cupon", method = RequestMethod.GET)
    public ModelAndView irACupones(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");
        String barrio1 = (String) session.getAttribute("barrio");
        DatosCerveceria datos = new DatosCerveceria();
        model.put("cerveceria",datos);
        List<Cerveceria> cervecerias = servicioCerveceria.buscarCervecerias();
        model.put("cervecerias",cervecerias);
        if(usuarioRecibido!=null){
            model.put("logeado","true");
        }
        return new ModelAndView("cupon",model);
    }

    @RequestMapping(path = "/generar-cupon", method = RequestMethod.POST)
    public ModelAndView generarCupon(@ModelAttribute("cerveceria") DatosCerveceria datos, HttpServletRequest request){
        ModelMap modelo = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");

        modelo.put("cerveceria", datos);

        Cerveceria cerveceria = servicioCerveceria.buscarCerveceriaPorNombre(datos.getNombre());


        modelo.put("cupon", "true");

        Random rnd = new Random();
        Integer number = rnd.nextInt(999999);
        modelo.put("codigo",number);

        modelo.put("nameCerve",datos.getNombre());

        Cupon cupon = new Cupon(number+"",cerveceria, usuarioRecibido);

        servicioUsuario.guardarCupon(cupon);

        return new ModelAndView("cupon", modelo);
    }
}

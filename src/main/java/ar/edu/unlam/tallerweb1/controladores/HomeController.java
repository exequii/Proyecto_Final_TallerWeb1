package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCerveceria;
import ar.edu.unlam.tallerweb1.servicios.ServicioFeedback;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioMapa;
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
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController extends HttpServlet {

    private ServicioCerveceria servicioCerveceria;
    private ServicioFeedback servicioFeedback;
    private ServicioMapa servicioMapa;

    @Autowired
    public HomeController(ServicioCerveceria servicioCerveceria, ServicioFeedback servicioFeedback, ServicioMapa servicioMapa){
        this.servicioCerveceria = servicioCerveceria;
        this.servicioFeedback = servicioFeedback;
        this.servicioMapa = servicioMapa;
    }


    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");
        String barrio1 = (String) session.getAttribute("barrio");
        DatosBuscador datos = new DatosBuscador();
        DatosCerveceria datosCerve = new DatosCerveceria();
        modelo.put("buscador", datos);
        modelo.put("datos-cerveceria",datosCerve);
        if(usuarioRecibido!=null){
            modelo.put("logeado","true");
        }
        if (usuarioRecibido.getLatitud() == null || usuarioRecibido.getLongitud() == null) {
            modelo.put("infoUsuario",0);
        } else {
            modelo.put("infoUsuario",1);
        }
        return new ModelAndView("home",modelo);
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/logout")
    public ModelAndView cerrarSesion(HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        HttpSession session = request.getSession();
        session.removeAttribute("usuario");
        session.removeAttribute("logeado");
        modelo.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", modelo);
    }

    @RequestMapping(path = "/buscar-cerveceria", method = RequestMethod.POST)
    public ModelAndView buscarCerveceria(@ModelAttribute("buscador") DatosBuscador buscador, HttpServletRequest request){
        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");

        try {
            switch (buscador.getTipo()){
                case "nombre":
                    Cerveceria encontrada = servicioCerveceria.buscarCerveceriaPorNombre(buscador.getNombre());
                    Double promedio = servicioCerveceria.obtenerElPromedioDePuntuacionPorNombre(buscador.getNombre());
                    encontrada.setPromedioPuntuacion(promedio);
                    model.put("cerveceriaEncontrada", encontrada);
                    break;

                case "radio":
                    if(usuarioRecibido.getDireccion() != null){
                        List<Cerveceria> cerveceriasFiltradasPorCercania = servicioCerveceria.filtrarCerveceriaPorCercania(usuarioRecibido,buscador.getNombre());
                        List<DatosCoordenadas> coordenadas = servicioMapa.obtenerListaDeCoordenadas(cerveceriasFiltradasPorCercania,false);
                        List<Cerveceria> cerveceriasFinal = servicioCerveceria.setearCerveceriaMejorPuntuada(cerveceriasFiltradasPorCercania);
                        model.put("coordenadas",coordenadas);
                        model.put("barrio", usuarioRecibido.getBarrio());
                        model.put("cervecerias",cerveceriasFinal);
                    } else{
                        model.put("msg", "Debe setear su direccion de domicilio.");
                    }
                    break;

                case "barrio":
                    List<Cerveceria> cervecerias = servicioCerveceria.buscarCerveceriaPorBarrio(buscador.getNombre());
                    List<DatosCoordenadas> coordenadas = servicioMapa.obtenerListaDeCoordenadas(cervecerias,true);
                    cervecerias = servicioCerveceria.setearCerveceriaMejorPuntuada(cervecerias);
                    model.put("coordenadas",coordenadas);
                    model.put("listaEncontrada",cervecerias);
                    model.put("barrio", buscador.getNombre());
                    break;
                }


        } catch (Exception e) {
            model.put("msg", "No se encontro una cerveceria con el parametro indicado.");
            DatosBuscador datos = new DatosBuscador();
            model.put("buscador", datos);
            return new ModelAndView("home", model);
        }

        return new ModelAndView("home", model);
    }





}

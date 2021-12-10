package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorRegistrarme {

    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorRegistrarme(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-registrarme")
    public ModelAndView irARegistrarme(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        DatosRegistro datos = new DatosRegistro();
        model.put("datos", datos);

        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario) session.getAttribute("usuario");
        if (usuarioRecibido != null) {
            model.put("logeado", "true");
        }

        return new ModelAndView("registro-usuario", model);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/registrarme")
    public ModelAndView registrarUsuario(@ModelAttribute("datos") DatosRegistro datos) {

        ModelMap model = new ModelMap();

        try {
            if (esValido(datos.getEmail(),datos.getClave(),datos.getRepiteClave())) {
                try {
                    servicioLogin.registrar(datos.getEmail(), datos.getClave());
                } catch (Exception e) {
                    model.put("msg", "El usuario ya existe");
                    return new ModelAndView("registro-usuario", model);
                }

                model.put("email", datos.getEmail());
                model.put("msg", "Registro Exitoso");

                DatosLogin datosLogin = new DatosLogin();
                datosLogin.setEmail(datos.getEmail());
                model.put("datosLogin", datosLogin);
                return new ModelAndView("login", model);
            }
        } catch (Exception e) {
            model.put("msg", e.getMessage());
            return new ModelAndView("registro-usuario", model);
        }
        model.put("msg", "Ha ocurrido un error");
        return new ModelAndView("registro-usuario", model);
    }

    private boolean esValido(String email, String clave, String repiteClave) throws Exception {
        if(email.endsWith(".com") && email.contains("@")){
            if(clave.equals(repiteClave)){
                return true;
            }
            else{
                throw new Exception("Las contrasenias no coinciden");
            }
        }
        else{
            throw new Exception("El usuario ingresado no es valido");
        }
    }

}

package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
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
public class ControladorLogin extends HttpServlet {

	private ServicioLogin servicioLogin;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin){
		this.servicioLogin = servicioLogin;
	}

	@RequestMapping("/login")
	public ModelAndView irALogin(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		modelo.put("datosLogin", new DatosLogin());

		HttpSession session = request.getSession();
		Usuario usuarioRecibido = (Usuario)session.getAttribute("usuario");
		if(usuarioRecibido!=null){
			modelo.put("logeado","true");
		}

		return new ModelAndView("login", modelo);
	}


	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("usuario", usuarioBuscado);
			request.getSession().setAttribute("logeado", true);

			model.put("usuario", usuarioBuscado);
			model.put("logeado","true");
			return new ModelAndView("redirect:/home",model);
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}




}

package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Feedback;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioFeedback;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ReviewController {

    private ServicioFeedback servicioFeedback;

    @Autowired
    public ReviewController(ServicioFeedback servicioFeedback) {
        this.servicioFeedback = servicioFeedback;
    }

    @RequestMapping("/review")
    public ModelAndView irAReview(HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        List<DatosReview> reviews = new LinkedList<>();
        List<Feedback> feedbacks = new LinkedList<>();
        //DatosReview review = new DatosReview();
        DatosReview review2 = new DatosReview();

        feedbacks = servicioFeedback.buscarTodosLosFeedbacks();

        if (feedbacks.size() != 0) {
            for (Feedback feedback : feedbacks) {

                DatosReview review = new DatosReview();

                review.setUsuario(feedback.getUsuario());
                review.setCerveceria(feedback.getCerveceria().getNombre());
                review.setCerveza(feedback.getCerveza());
                review.setPuntuacion(feedback.getPuntuacion());
                review.setComentario(feedback.getComentario());

                reviews.add(review);
                modelo.put("datosReview", reviews);

                //review.setCerveceria("Antares");
                //review.setPuntuacion(5);
                //review.setCerveza("Honey");
                //review.setComentario("Muy lindo");

                //review2.setCerveceria("Cervelar");
                //review2.setPuntuacion(4);
                //review2.setCerveza("IPA");
                //review2.setComentario("Muy genialñ");

                //reviews.add(review);
                //reviews.add(review2);

                //modelo.put("datosReview", reviews);
            }
        } else {
            //Si envio un mensaje en el msg, no renderiza ningun review
            //AGREGAR UN IF DE QUE SI LA BUSQUEDA DE FEEDBACKS NO TRAE NADA, SE ENVIA SOLO EL MSG
            modelo.put("msg", "No se encontraron reviews");
        }

        HttpSession session = request.getSession();
        Usuario usuarioRecibido = (Usuario) session.getAttribute("usuario");
        if (usuarioRecibido != null) {
            modelo.put("logeado", "true");
        }

        return new ModelAndView("review", modelo);
    }

    @RequestMapping(path = "reviews-filtradas",method = RequestMethod.GET)
    public ModelAndView reviewsFiltradas(@RequestParam("puntuacion") Integer puntuacion) {
        ModelMap model = new ModelMap();
        List<DatosReview> reviews = new LinkedList<>();
        List<Feedback> feedbacks = servicioFeedback.filtrarPorPuntuacion(puntuacion);
        if (feedbacks.size() != 0) {
            for (Feedback feedback : feedbacks) {
                DatosReview review = new DatosReview();

                review.setUsuario(feedback.getUsuario());
                review.setCerveceria(feedback.getCerveceria().getNombre());
                review.setCerveza(feedback.getCerveza());
                review.setPuntuacion(feedback.getPuntuacion());
                review.setComentario(feedback.getComentario());

                reviews.add(review);
                model.addAttribute("datosReview", reviews);
            }
        } else {
            model.put("msg", "No se encontraron reviews");
        }
        return new ModelAndView("review", model);
    }


}
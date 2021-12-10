package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class GraficosController extends HttpServlet {
    private ServicioCerveceria servicioCerveceria;

    @Autowired
    public GraficosController(ServicioCerveceria servicioCerveceria) {
        this.servicioCerveceria = servicioCerveceria;
    }

    @RequestMapping(path = "/grafico")
    public ModelAndView crearGrafico() throws Exception {
        ModelMap model = new ModelMap();

        DefaultPieDataset datos = new DefaultPieDataset();

        List<Cerveceria> cervecerias = servicioCerveceria.buscarCervecerias();
        for(Cerveceria cerveceriaAMostrar : cervecerias){
            Double promedio = servicioCerveceria.obtenerElPromedioDePuntuacionPorNombre(cerveceriaAMostrar.getNombre());
            datos.setValue(cerveceriaAMostrar.getNombre(),Math.round(promedio));
        }

        JFreeChart grafico = ChartFactory.createPieChart("Reservas",datos, true,true,false);

        grafico.setBackgroundPaint(Color.GRAY);
        PiePlot plot = (PiePlot) grafico.getPlot();
        plot.setNoDataMessage("No hay datos disponibles");
        plot.setCircular(false);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        File archivo = new File("C:/Users/ezequ/Documents/GitHub/TallerWeb2_Only_Fans/src/main/webapp/img/estadisticas.jpeg");

        try {
            ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            if(archivo.exists()){
                archivo.delete();
                ChartUtils.saveChartAsJPEG(archivo, 1, grafico, 600, 400);
            }else{
                ChartUtils.saveChartAsJPEG(archivo, 1, grafico, 600, 400);
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return new ModelAndView("grafico",model);
    }


    }

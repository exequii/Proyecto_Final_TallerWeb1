package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosCoordenadas;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service("servicioMapa")
@Transactional
public class ServicioMapaImpl implements ServicioMapa{

    private static final String API_KEY = "AIzaSyBYQIPNiW4ir4_kCFbD7ekM1EDwARZ3vTI";

    @Override
    public List<Double> buscarCoordenadasPorDireccion(String direccion) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context,direccion).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double latitud = results[0].geometry.location.lat;
        double longitud = results[0].geometry.location.lng;
        List<Double> coordenadas = new LinkedList<>();
        coordenadas.add(latitud);
        coordenadas.add(longitud);
        return coordenadas;
    }

    @Override
    public Double obtenerDistanciaEntreDosCoordenadas(DatosCoordenadas origen, DatosCoordenadas destino) throws IOException {

        Double latitudOrigen = origen.getLatitud();
        Double longitudOrigen = origen.getLongitud();
        Double latitudDestino = destino.getLatitud();
        Double longitudDestino = destino.getLongitud();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+latitudOrigen+","+longitudOrigen+"&destinations="+latitudDestino+","+longitudDestino+"&key=AIzaSyBYQIPNiW4ir4_kCFbD7ekM1EDwARZ3vTI")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        String respuesta = response.body().string();

        JSONObject json = new JSONObject(respuesta);

        String valor = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");

        valor = valor.replace(" km", "");

        Double distancia = Double.parseDouble(valor);

        return distancia;
    }

    @Override
    public List<DatosCoordenadas> obtenerListaDeCoordenadas(List<Cerveceria> cervecerias, Boolean validator) {
        List<DatosCoordenadas> listaCoordenadas = new LinkedList<>();
        for (Cerveceria cerveceriasAFiltrar : cervecerias){
            if(cerveceriasAFiltrar.getMostrar() == true || validator == true){
                DatosCoordenadas coordenadas = new DatosCoordenadas();
                coordenadas.setLatitud(cerveceriasAFiltrar.getLatitud());
                coordenadas.setLongitud(cerveceriasAFiltrar.getLongitud());
                coordenadas.setIndex(cervecerias.indexOf(cerveceriasAFiltrar)+1);
                listaCoordenadas.add(coordenadas);
            }
        }
        return listaCoordenadas;
    }
}

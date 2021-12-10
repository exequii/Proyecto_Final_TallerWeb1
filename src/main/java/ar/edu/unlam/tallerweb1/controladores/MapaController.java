package ar.edu.unlam.tallerweb1.controladores;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class MapaController {

    private static final String API_KEY = "ACA VA LA KEY DE GOOGLE API";

    @RequestMapping(path = "/mapa")
    public ModelAndView mapa(String direccion) {
        ModelMap model = new ModelMap();
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

        model.put("barrio","palermo");
        double latitud = results[0].geometry.location.lat;
        double longitud = results[0].geometry.location.lng;

        model.put("latitud",latitud);
        model.put("longitud",longitud);

        return new ModelAndView("mapa",model);
    }

    @RequestMapping(path = "/distancia")
    public ModelAndView medirDistancia() throws IOException {

        ModelMap model = new ModelMap();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/distancematrix/json?origins=-34.657711,-58.494084&destinations=-34.656008,-58.492571&key=ACAVALAKEYAPI")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        String respuesta = response.body().string();

        JSONObject json = new JSONObject(respuesta);

        String valor = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");

        Double distancia = Double.parseDouble(valor);

        return new ModelAndView("mapa",model);
    }



}

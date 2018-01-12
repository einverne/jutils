package com.jutils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jutils.gson.deserializer.DiffTypeDeserializer;
import com.jutils.gson.deserializer.SampleDeserializer;
import com.jutils.gson.entity.Car;
import com.jutils.gson.entity.DiffTypeBase;
import com.jutils.gson.entity.PersonAnnotation;
import com.jutils.gson.entity.PersonStrategy;
import com.jutils.gson.entity.PersonTransient;
import com.jutils.gson.entity.SampleObject;
import com.jutils.gson.strategy.ExcluStrategy;
import com.jutils.retrofit.ApiXu;
import com.jutils.retrofit.WeatherRetro;
import com.jutils.retrofit.entity.Weather;
import net.sf.json.JSONObject;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 18-1-8.
 */
public class GsonTest {

    Gson gson = new GsonBuilder().registerTypeAdapter(SampleObject.class, new SampleDeserializer()).create();

    @Test
    public void createGson() {
        String demoStr = "{\n" +
                "    \"ENCUESTA\": {\n" +
                "        \"ALOJAMIENTO\": {\n" +
                "            \"RESIDENCIA\": [\n" +
                "                {\n" +
                "                    \"ID_PROVINCIA_ISLA\": \"ES511\",\n" +
                "                    \"MOVIMIENTO\": [\n" +
                "                        {\n" +
                "                            \"SALIDAS\": 0,\n" +
                "                            \"PERNOCTACIONES\": 3,\n" +
                "                            \"N_DIA\": \"08\",\n" +
                "                            \"ENTRADAS\": 3\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"MOVIMIENTO\": [\n" +
                "                        {\n" +
                "                            \"SALIDAS\": 0,\n" +
                "                            \"PERNOCTACIONES\": 3,\n" +
                "                            \"N_DIA\": \"06\",\n" +
                "                            \"ENTRADAS\": 3\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"ID_PAIS\": \"CHN\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"CABECERA\": {\n" +
                "            \"FECHA_REFERENCIA\": {\n" +
                "                \"MES\": 11,\n" +
                "                \"ANYO\": 2017\n" +
                "            },\n" +
                "            \"NOMBRE_ESTABLECIMIENTO\": \"UNKNOWN\"\n" +
                "\n" +
                "        }\n" +
                "    }\n" +
                "}";
        SampleObject sampleObject = gson.fromJson(demoStr, SampleObject.class);
        System.out.println(sampleObject);
    }

    @Test
    public void partJson() {
        String response = "{\n" +
                "    \"response\": {\n" +
                "        \"profile\": {\n" +
                "            \"id\": 8873\n" +
                "       }\n" +
                "    }\n" +
                "}";

        JSONObject responseObject = new JSONObject().fromObject(response);
        JSONObject jsonObject = responseObject.optJSONObject("response");

    }

    @Test
    public void serializeWithExpose() {
        Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        PersonTransient personTransient = new PersonTransient(1L, "Name", "into");
        String s = g.toJson(personTransient);
        System.out.println(s);
    }

    @Test
    public void testApiXu() {
        ApiXu instance = WeatherRetro.getInstance();
        Call<Weather> weather = instance.getWeather();
        try {
            Response<Weather> response = weather.execute();
            if (response.isSuccessful()) {
                Weather body = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void diffType() {
        Type listType = new TypeToken<ArrayList<DiffTypeBase>>() {
        }.getType();
        Gson g = new GsonBuilder().registerTypeAdapter(listType, new DiffTypeDeserializer()).create();
        List<DiffTypeBase> base = g.fromJson("", listType);
    }

    @Test
    public void ignoreField() {
        // way1

        PersonTransient personTransient = new PersonTransient(2L, "name", "tro", 18);
        String s = new Gson().toJson(personTransient);
        System.out.println(s);

        Gson g1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String s1 = g1.toJson(personTransient);
        System.out.println(s1);
    }

    @Test
    public void ignoreWithStrategy() {
        PersonStrategy strategy = new PersonStrategy(3L, "name", "intro", 20);
        strategy.setCar(new Car("BMW", 1000000));
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExcluStrategy()).create();
        String s = gson.toJson(strategy);
        System.out.println(s);
    }

    @Test
    public void annotationTest() {
        PersonAnnotation annotation = new PersonAnnotation(1L, "name", 10);
        Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
        String s = gson.toJson(annotation);
        System.out.println(s);
    }
}

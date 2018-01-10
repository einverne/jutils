package com.jutils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONObject;
import org.junit.Test;

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
}

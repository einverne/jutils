package com.jutils.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 18-1-10.
 */
public class SampleDeserializer implements JsonDeserializer<SampleObject> {
    @Override
    public SampleObject deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        SampleObject sampleObject = new SampleObject();
        SampleObject.Encuesta encuestaObject = new SampleObject.Encuesta();
        SampleObject.ALOJAMIENTO alojamientoObject = new SampleObject.ALOJAMIENTO();

        List<ResidenciaBase> baseList = new ArrayList<>();
        JsonObject encuesta = jsonElement.getAsJsonObject().getAsJsonObject("ENCUESTA");
        JsonObject alojamiento = encuesta.getAsJsonObject("ALOJAMIENTO");
        JsonArray residencia = alojamiento.getAsJsonArray("RESIDENCIA");

        for (JsonElement element : residencia) {
            if (element.getAsJsonObject().has("ID_PROVINCIA_ISLA")) {
                ResidenciaProv prov = new Gson().fromJson(element.getAsJsonObject().toString(), ResidenciaProv.class);
                baseList.add(prov);
            }
            if (element.getAsJsonObject().has("ID_PAIS")) {
                ResidenciaPais residenciaPais = new Gson().fromJson(element.getAsJsonObject().toString(), ResidenciaPais.class);
                baseList.add(residenciaPais);
            }
        }
        alojamientoObject.setResidencia(baseList);
        encuestaObject.setAlojamiento(alojamientoObject);

        JsonObject cabecera = encuesta.getAsJsonObject().getAsJsonObject("CABECERA");
        SampleObject.Cabecera cabecera1 = new Gson().fromJson(cabecera.toString(), SampleObject.Cabecera.class);
        encuestaObject.setCabecera(cabecera1);
        sampleObject.setEncuesta(encuestaObject);
        return sampleObject;
    }
}

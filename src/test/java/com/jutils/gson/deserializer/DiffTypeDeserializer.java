package com.jutils.gson.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jutils.gson.entity.Assinatura;
import com.jutils.gson.entity.DiffTypeBase;
import com.jutils.gson.entity.Unico;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 18-1-11.
 */
public class DiffTypeDeserializer implements JsonDeserializer<List<DiffTypeBase>> {
    @Override
    public List<DiffTypeBase> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        List<DiffTypeBase> diffTypeList = new ArrayList();
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        for (JsonElement e : asJsonArray) {
            String typeValue = e.getAsJsonObject().getAsJsonPrimitive("type").getAsString();
            if ("unico".equals(typeValue)) {
                Unico unico = new Gson().fromJson(e.getAsJsonObject().toString(), Unico.class);
                diffTypeList.add(unico);
            } else if ("assinatura".equals(typeValue)) {
                Assinatura assinatura = new Gson().fromJson(e.getAsJsonObject().toString(), Assinatura.class);
                diffTypeList.add(assinatura);
            }
        }
        return diffTypeList;
    }
}

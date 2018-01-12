package com.jutils.gson.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mi on 18-1-10.
 */
public class ResidenciaPais extends ResidenciaBase {

    @SerializedName("ID_PAIS")
    private String idPais;

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }
}

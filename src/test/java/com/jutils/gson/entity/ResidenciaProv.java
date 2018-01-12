package com.jutils.gson.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mi on 18-1-10.
 */
public class ResidenciaProv extends ResidenciaBase {
    @SerializedName("ID_PROVINCIA_ISLA")
    private String idProv;

    public String getIdProv() {
        return idProv;
    }

    public void setIdProv(String idProv) {
        this.idProv = idProv;
    }
}

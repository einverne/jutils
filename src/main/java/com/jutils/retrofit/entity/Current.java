package com.jutils.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mi on 18-1-10.
 */
public class Current {
    @SerializedName("temp_c")
    @Expose
    private int tempC;
    @SerializedName("temp_f")
    @Expose
    private double tempF;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("wind_mph")
    @Expose
    private float windMph;
    @SerializedName("precip_mm")
    @Expose
    private float precipMm;
    @SerializedName("humidity")
    @Expose
    private int humidity;

    public int getTempC() {
        return tempC;
    }

    public void setTempC(int tempC) {
        this.tempC = tempC;
    }

    public double getTempF() {
        return tempF;
    }

    public void setTempF(double tempF) {
        this.tempF = tempF;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public float getWindMph() {
        return windMph;
    }

    public void setWindMph(float windMph) {
        this.windMph = windMph;
    }

    public void setWindMph(int windMph) {
        this.windMph = windMph;
    }

    public float getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(float precipMm) {
        this.precipMm = precipMm;
    }

    public void setPrecipMm(int precipMm) {
        this.precipMm = precipMm;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
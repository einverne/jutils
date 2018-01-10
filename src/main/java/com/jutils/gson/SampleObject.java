package com.jutils.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mi on 18-1-10.
 */
public class SampleObject {
    @SerializedName("ENCUESTA")
    private Encuesta encuesta;

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public static class Encuesta {
        @SerializedName("ALOJAMIENTO")
        private ALOJAMIENTO alojamiento;

        @SerializedName("CABECERA")
        private Cabecera cabecera;

        public ALOJAMIENTO getAlojamiento() {
            return alojamiento;
        }

        public void setAlojamiento(ALOJAMIENTO alojamiento) {
            this.alojamiento = alojamiento;
        }

        public Cabecera getCabecera() {
            return cabecera;
        }

        public void setCabecera(Cabecera cabecera) {
            this.cabecera = cabecera;
        }
    }

    public static class ALOJAMIENTO {
        @SerializedName("RESIDENCIA")
        private List<ResidenciaBase> residencia;

        public List<ResidenciaBase> getResidencia() {
            return residencia;
        }

        public void setResidencia(List<ResidenciaBase> residencia) {
            this.residencia = residencia;
        }
    }

    public class Cabecera {
        @SerializedName("FECHA_REFERENCIA")
        private FECHA_REFERENCIA fecha_referencia;

        @SerializedName("NOMBRE_ESTABLECIMIENTO")
        private String nombre_establecimiento;

        public FECHA_REFERENCIA getFecha_referencia() {
            return fecha_referencia;
        }

        public void setFecha_referencia(FECHA_REFERENCIA fecha_referencia) {
            this.fecha_referencia = fecha_referencia;
        }

        public String getNombre_establecimiento() {
            return nombre_establecimiento;
        }

        public void setNombre_establecimiento(String nombre_establecimiento) {
            this.nombre_establecimiento = nombre_establecimiento;
        }
    }

    public class FECHA_REFERENCIA {
        private int MES;
        private int ANYO;

        public int getMES() {
            return MES;
        }

        public void setMES(int MES) {
            this.MES = MES;
        }

        public int getANYO() {
            return ANYO;
        }

        public void setANYO(int ANYO) {
            this.ANYO = ANYO;
        }
    }
}

package com.jutils.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mi on 18-1-10.
 */
public abstract class ResidenciaBase {
    @SerializedName("MOVIMIENTO")
    private List<Movimiento> movimiento;

    public List<Movimiento> getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(List<Movimiento> movimiento) {
        this.movimiento = movimiento;
    }

    public static class Movimiento {

        @SerializedName("SALIDAS")
        private int salidas;

        @SerializedName("PERNOCTACIONES")
        private int pernoctaciones;

        @SerializedName("N_DIA")
        private String nDia;

        @SerializedName("ENTRADAS")
        private int entradas;

        public Movimiento(int salidas, int pernoctaciones, String nDia, int entradas) {
            this.salidas = salidas;
            this.pernoctaciones = pernoctaciones;
            this.nDia = nDia;
            this.entradas = entradas;
        }

        public int getSalidas() {
            return salidas;
        }

        public void setSalidas(int salidas) {
            this.salidas = salidas;
        }

        public int getPernoctaciones() {
            return pernoctaciones;
        }

        public void setPernoctaciones(int pernoctaciones) {
            this.pernoctaciones = pernoctaciones;
        }

        public String getnDia() {
            return nDia;
        }

        public void setnDia(String nDia) {
            this.nDia = nDia;
        }

        public int getEntradas() {
            return entradas;
        }

        public void setEntradas(int entradas) {
            this.entradas = entradas;
        }
    }
}

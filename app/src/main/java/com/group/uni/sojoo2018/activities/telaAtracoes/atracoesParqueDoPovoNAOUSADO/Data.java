package com.group.uni.sojoo2018.activities.telaAtracoes.atracoesParqueDoPovoNAOUSADO;

/**
 * Created by fabii on 31/01/2018.
 */

public class Data {

    private String diaS, dia, mes, hora, minuto;

    public Data(String diaS, String dia, String mes, String hora, String minuto) {
        this.diaS = diaS;
        this.dia = dia;
        this.mes = mes;
        this.hora = hora;
        this.minuto = minuto;
    }

    public String getDiaS() {
        return diaS;
    }

    public void setDiaS(String diaS) {
        this.diaS = diaS;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }
}

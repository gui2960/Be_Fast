package com.guilhermeluftlab.befast.models;

import java.util.Calendar;

public class Contrato {
    private Calendar calendar;
    private String idUsuario;
    private String idContratado;
    private Servico servico;

    public Contrato(Calendar calendar, String idUsuario, String idContratado, Servico servico) {
        this.calendar = calendar;
        this.idUsuario = idUsuario;
        this.idContratado = idContratado;
        this.servico = servico;
    }

    public Contrato(){

    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdContratado() {
        return idContratado;
    }

    public void setIdContratado(String idContratado) {
        this.idContratado = idContratado;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}

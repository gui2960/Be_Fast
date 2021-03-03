package com.guilhermeluftlab.befast.models;

public class Servico {
    private String nome;
    private String valor;
    private String tempoMinutos;

    public Servico(String nome, String valor, String tempoMinutos) {
        this.nome = nome;
        this.valor = valor;
        this.tempoMinutos = tempoMinutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(String tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    @Override
    public String toString() {
        return "Nome: '" + nome + " "+ "Valor: " +  valor + " " + "Duração (Minutos): " + tempoMinutos;
    }
}

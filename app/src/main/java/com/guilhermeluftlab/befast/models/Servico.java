package com.guilhermeluftlab.befast.models;

public class Servico {
    private String nome;
    private double valor;
    private int tempoMinutos;

    public Servico(String nome, double valor, int tempoMinutos) {
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(int tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    @Override
    public String toString() {
        return "Nome: '" + nome + " "+ "Valor: " +  valor + " " + "Duração (Minutos): " + tempoMinutos;
    }
}

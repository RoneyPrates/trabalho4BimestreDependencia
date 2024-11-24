package com.example.trabalho4bimestre.model;

public class Produto {
    private int idProduto;
    private String descricao;
    private double valor;

    //Construtor
    public Produto() {

    }

    //Getters And Setters
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

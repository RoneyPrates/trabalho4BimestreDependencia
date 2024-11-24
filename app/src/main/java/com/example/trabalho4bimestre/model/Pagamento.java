package com.example.trabalho4bimestre.model;

public class Pagamento {
    private int idPagamento;
    private String tipoPagamento;

    //Construtor

    public Pagamento() {

    }

    //Getters and Setters

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}

package com.example.trabalho4bimestre.model;


public class Venda {
    private int idVenda;
    private String produto;
    private String pagamento;
    private String vendedor;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    //Construtor


    public Venda() {
    }

    public Venda(int idVenda, String produto, String pagamento, String vendedor, int quantidade, double valorUnitario, double valorTotal) {
        this.idVenda = idVenda;
        this.produto = produto;
        this.pagamento = pagamento;
        this.vendedor = vendedor;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    //Getters and Setters


    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
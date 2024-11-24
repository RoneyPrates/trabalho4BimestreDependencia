package com.example.trabalho4bimestre.controller;

import android.content.Context;
import android.widget.Spinner;

import com.example.trabalho4bimestre.dao.ProdutoDao;
import com.example.trabalho4bimestre.dao.VendaDao;
import com.example.trabalho4bimestre.model.Produto;
import com.example.trabalho4bimestre.model.Venda;

import java.util.ArrayList;

public class VendaController {

    private Context context;


    public VendaController(Context context) {
        this.context = context;
    }

    public String salvarVenda(String produto, String quantidade, String tipoPagamento, String vendedor){
        try {
            if (produto.equals("") || produto.isEmpty()){
                return "Informe o produto que voce deseja";
            }
            if (vendedor.equals("") || vendedor.isEmpty()) {
                return "Informe o vendedor";
            }
            if (tipoPagamento.equals("") || tipoPagamento.isEmpty()) {
                return "Informe o tipo de pagamento";
            }
            if (quantidade.equals("") || quantidade.isEmpty()){
                return "Informe quantos itens voce deseja";
            }
            Produto produtoSelecionado = ProdutoDao.getInstancia(context).getByDescricao(produto);

            Venda venda = new Venda();
            venda.setProduto(produto);
            venda.setVendedor(vendedor);
            venda.setPagamento(tipoPagamento);
            venda.setQuantidade(Integer.parseInt(quantidade));
            venda.setValorUnitario(produtoSelecionado.getValor());
            venda.setValorTotal(produtoSelecionado.getValor() * Integer.parseInt(quantidade));

            VendaDao.getInstancia(context).insert(venda);
        }catch (Exception ex){
            return "Erro ao cadastrar venda";
        }
        return null;
    }

    public ArrayList<Venda> retornarTodasVendas(){
        return VendaDao.getInstancia(context).getAll();
    }
}

package com.example.trabalho4bimestre.controller;

import android.content.Context;

import com.example.trabalho4bimestre.dao.ProdutoDao;
import com.example.trabalho4bimestre.model.Produto;

import java.util.ArrayList;

public class ProdutoController {

    private Context context;

    public ProdutoController(Context context){
        this.context = context;
    }

    public String salvarProduto(String descricao, String valor){
        try{
            if(descricao.equals("") || descricao.isEmpty()){
                return "Informe uma descrição!";
            }
            if(valor.equals("") || valor.isEmpty()){
                return "Informe o valor do produto";
            }

            Produto produto = ProdutoDao.getInstancia(context).getByDescricao(descricao);

            if (produto != null){
                return "Este produto já está cadastrado no sistema";
            }else{
                produto = new Produto();
                produto.setDescricao(descricao);
                produto.setValor(Double.parseDouble(valor));

                ProdutoDao.getInstancia(context).insert(produto);
            }
        }catch (Exception ex){
            return "Erro ao Gravar o Produto";
        }
        return null;
    }

    public ArrayList<Produto> retornarTodosProdutos(){
        return ProdutoDao.getInstancia(context).getAll();
    }

}

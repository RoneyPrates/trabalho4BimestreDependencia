package com.example.trabalho4bimestre.controller;

import android.content.Context;

import com.example.trabalho4bimestre.dao.PagamentoDao;
import com.example.trabalho4bimestre.model.Pagamento;

import java.util.ArrayList;

public class PagamentoController {
    private Context context;

    public PagamentoController(Context context){
        this.context = context;
    }

    public String salvarPagamento(String descricao){
        try{
            if(descricao.equals("") || descricao.isEmpty()){
                return "Informe o tipo de pagamento!";
            }

            Pagamento pagamento = PagamentoDao.getInstancia(context).getByTipoPagamento(descricao);

            if (pagamento != null){
                return "O tipo de pagamento ("+descricao+") já está cadastrado";
            }else{
                pagamento = new Pagamento();
                pagamento.setTipoPagamento(descricao);

                PagamentoDao.getInstancia(context).insert(pagamento);
            }


        }catch (Exception ex){
            return "Erro ao cadastrar tipo de pagamento!";
        }

        return null;
        }

    public ArrayList<Pagamento> retornarTodosPagmentos(){
        return PagamentoDao.getInstancia(context).getAll();
    }

}


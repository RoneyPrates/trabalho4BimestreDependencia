package com.example.trabalho4bimestre.controller;

import android.content.Context;

import com.example.trabalho4bimestre.dao.EnderecoDao;
import com.example.trabalho4bimestre.model.Endereco;

import java.util.ArrayList;

public class EnderecoController {
    private Context context;

    public EnderecoController(Context context) {
        this.context = context;
    }

    public String salvarEndereco(int codigo, String logradouro, String numero, String bairro, String cidade, String uf) {
        try {
            if (logradouro == null || logradouro.isEmpty()) {
                return "Informe o logradouro!";
            }
            if (bairro == null || bairro.isEmpty()) {
                return "Informe o bairro!";
            }
            if (cidade == null || cidade.isEmpty()) {
                return "Informe a cidade!";
            }
            if (uf == null || uf.isEmpty()) {
                return "Informe o UF!";
            }

            Endereco novoEndereco = new Endereco(codigo, logradouro, numero, bairro, cidade, uf);
            EnderecoDao.getInstancia(context).insert(novoEndereco);
        } catch (Exception ex) {
            return "Erro ao cadastrar endereço: " + ex.getMessage();
        }

        return null;
    }

    public ArrayList<Endereco> retornarTodosEnderecos() {
        return EnderecoDao.getInstancia(context).getAll();
    }
}

package com.example.trabalho4bimestre.controller;

import android.content.Context;

import com.example.trabalho4bimestre.dao.ClienteDao;
import com.example.trabalho4bimestre.model.Cliente;
import java.util.ArrayList;

public class ClienteController {
    private Context context;

    public ClienteController(Context context){
        this.context = context;
    }

    public String salvarCliente(String nome, String cpf){
        try {
            if (nome == null || nome.isEmpty()) {
                return "Informe o nome do cliente!";
            }
            if (cpf == null || cpf.isEmpty()) {
                return "Informe o CPF do cliente!";
            }

            Cliente clienteExistente = ClienteDao.getInstancia(context).getByCpf(cpf);

            if (clienteExistente != null) {
                return "O cliente com o CPF (" + cpf + ") já está cadastrado!";
            } else {
                Cliente novoCliente = new Cliente(nome, cpf);
                ClienteDao.getInstancia(context).insert(novoCliente);
            }

        } catch (Exception ex) {
            return "Erro ao cadastrar cliente: " + ex.getMessage();
        }

        return null;
    }

    public ArrayList<Cliente> retornarTodosClientes() {
        return ClienteDao.getInstancia(context).getAll();
    }
}

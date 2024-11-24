package com.example.trabalho4bimestre.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.adapter.PagamentoListAdapter;
import com.example.trabalho4bimestre.adapter.ProdutoListAdapter;
import com.example.trabalho4bimestre.controller.PagamentoController;
import com.example.trabalho4bimestre.controller.ProdutoController;
import com.example.trabalho4bimestre.model.Pagamento;
import com.example.trabalho4bimestre.model.Produto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PagamentoActivity extends AppCompatActivity {

    private EditText edTipoPagamento;

    private PagamentoController controller;

    private View viewAlert;
    private AlertDialog dialog;
    private RecyclerView rvPagamentos;

    private FloatingActionButton btCadastroPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        controller = new PagamentoController(this);
        rvPagamentos = findViewById(R.id.rvPagamentos);
        btCadastroPagamento = findViewById(R.id.btCadastroPagamento);

        btCadastroPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastro();
            }
        });
        atualizarLista();
    }


    private void abrirCadastro() {
        viewAlert = getLayoutInflater().inflate(R.layout.dialog_cadastro_pagamento, null);

        edTipoPagamento = viewAlert.findViewById(R.id.edTipoPagamento);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastro de Pagamento");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Salvar", null);
        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDados();
                    }
                });
            }
        });
        dialog.show();

    }

    public void salvarDados(){
        String retorno = controller.salvarPagamento(edTipoPagamento.getText().toString());
        if(retorno != null){
            if(retorno.contains("Tipo de Pagamento")){
                edTipoPagamento.setError(retorno);
                edTipoPagamento.requestFocus();
            }
        }else{
            Toast.makeText(this,
                    "Tipo de Pagamento salvo com sucesso!",
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();
            atualizarLista();
        }
    }

    private void atualizarLista(){
        ArrayList<Pagamento> listaPagamento = controller.retornarTodosPagmentos();
        PagamentoListAdapter adapter = new PagamentoListAdapter(listaPagamento, this);
        rvPagamentos.setLayoutManager(new LinearLayoutManager(this));
        rvPagamentos.setAdapter(adapter);
    }
}
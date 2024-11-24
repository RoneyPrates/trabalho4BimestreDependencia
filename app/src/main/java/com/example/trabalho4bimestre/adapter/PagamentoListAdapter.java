package com.example.trabalho4bimestre.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.model.Pagamento;

import java.util.ArrayList;

public class PagamentoListAdapter extends RecyclerView.Adapter<PagamentoListAdapter.ViewHolder> {

    private ArrayList<Pagamento> listaPagamento;
    private Context context;

    public PagamentoListAdapter(ArrayList<Pagamento> listaPagamento, Context context) {
        this.listaPagamento = listaPagamento;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_pagamento,
                parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pagamento pagamentoSelecionado = listaPagamento.get(position);
        holder.tvCodigo.setText(String.valueOf(pagamentoSelecionado.getIdPagamento()));
        holder.tvTipoPagamento.setText(pagamentoSelecionado.getTipoPagamento());

    }

    @Override
    public int getItemCount() {
        return this.listaPagamento.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCodigo;
        public TextView tvTipoPagamento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvTipoPagamento = itemView.findViewById(R.id.tvTipoPagamento);
        }
    }
}

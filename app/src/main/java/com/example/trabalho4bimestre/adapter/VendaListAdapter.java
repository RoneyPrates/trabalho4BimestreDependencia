package com.example.trabalho4bimestre.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.model.Venda;

import java.util.ArrayList;

public class VendaListAdapter extends RecyclerView.Adapter<VendaListAdapter.ViewHolder> {

    private ArrayList<Venda> listaVenda;
    private Context context;

    public VendaListAdapter(ArrayList<Venda> listaVenda, Context context) {
        this.listaVenda = listaVenda;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listitem = inflater.inflate(R.layout.item_list_venda, parent, false);

        return new ViewHolder(listitem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Venda vendaSelecionada = listaVenda.get(position);
        holder.tvCodigo.setText(String.valueOf(vendaSelecionada.getIdVenda()));
        holder.tvProduto.setText(String.valueOf(vendaSelecionada.getProduto()));
        holder.tvQuantidade.setText(String.valueOf(vendaSelecionada.getQuantidade()));
        holder.tvValorUnitario.setText(String.valueOf("R$ " + vendaSelecionada.getValorUnitario()));
        holder.tvValorTotal.setText(String.valueOf("R$ " + vendaSelecionada.getValorTotal()));
    }

    @Override
    public int getItemCount() {
        return this.listaVenda.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCodigo;
        public TextView tvProduto;
        public TextView tvQuantidade;
        public TextView tvValorUnitario;
        public TextView tvValorTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvProduto = itemView.findViewById(R.id.tvProduto);
            this.tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            this.tvValorUnitario = itemView.findViewById(R.id.tvValorUnitario);
            this.tvValorTotal = itemView.findViewById(R.id.tvValorTotal);
        }
    }
}

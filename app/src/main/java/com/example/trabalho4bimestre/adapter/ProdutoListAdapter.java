package com.example.trabalho4bimestre.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.model.Produto;

import java.util.ArrayList;

public class ProdutoListAdapter extends RecyclerView.Adapter<ProdutoListAdapter.ViewHolder> {
    private ArrayList<Produto> listaProduto;
    private Context context;


    public ProdutoListAdapter(ArrayList<Produto> listaProduto, Context context) {
        this.listaProduto = listaProduto;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listitem = inflater.inflate(R.layout.item_list_produto, parent, false);

        return new ViewHolder(listitem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produtoSelecionado = listaProduto.get(position);
        holder.tvCodigo.setText(String.valueOf(produtoSelecionado.getIdProduto()));
        holder.tvDescricao.setText(produtoSelecionado.getDescricao());
        holder.tvValor.setText(String.valueOf("R$ " + produtoSelecionado.getValor()));
    }

    @Override
    public int getItemCount() {
        return this.listaProduto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCodigo;
        public TextView tvDescricao;
        public TextView tvValor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvDescricao = itemView.findViewById(R.id.tvDescriçao);
            this.tvValor = itemView.findViewById(R.id.tvValor);
        }
    }
}

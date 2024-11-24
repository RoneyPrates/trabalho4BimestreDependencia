package com.example.trabalho4bimestre.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.model.Endereco;

import java.util.ArrayList;

public class EnderecoListAdapter extends RecyclerView.Adapter<EnderecoListAdapter.ViewHolder> {

    private ArrayList<Endereco> listaEnderecos;
    private Context context;

    public EnderecoListAdapter(ArrayList<Endereco> listaEnderecos, Context context) {
        this.listaEnderecos = listaEnderecos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_endereco, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Endereco enderecoSelecionado = listaEnderecos.get(position);

        // Bindando todos os campos no holder
        holder.tvCodigoEndereco.setText(String.valueOf(enderecoSelecionado.getCodigo()));
        holder.tvLogradouroEndereco.setText(enderecoSelecionado.getLogradouro());
        holder.tvNumeroEndereco.setText(enderecoSelecionado.getNumero());
        holder.tvBairroEndereco.setText(enderecoSelecionado.getBairro());
        holder.tvCidadeEndereco.setText(enderecoSelecionado.getCidade());
        holder.tvUfEndereco.setText(enderecoSelecionado.getUf());
    }

    @Override
    public int getItemCount() {
        return this.listaEnderecos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCodigoEndereco;
        public TextView tvLogradouroEndereco;
        public TextView tvNumeroEndereco;
        public TextView tvBairroEndereco;
        public TextView tvCidadeEndereco;
        public TextView tvUfEndereco;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvCodigoEndereco = itemView.findViewById(R.id.tvCodigoEndereco);
            this.tvLogradouroEndereco = itemView.findViewById(R.id.tvLogradouroEndereco);
            this.tvNumeroEndereco = itemView.findViewById(R.id.tvNumeroEndereco);
            this.tvBairroEndereco = itemView.findViewById(R.id.tvBairroEndereco);
            this.tvCidadeEndereco = itemView.findViewById(R.id.tvCidadeEndereco);
            this.tvUfEndereco = itemView.findViewById(R.id.tvUfEndereco);
        }
    }
}

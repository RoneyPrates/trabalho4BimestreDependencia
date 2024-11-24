package com.example.trabalho4bimestre.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.model.Cliente;

import java.util.ArrayList;

public class ClienteListAdapter extends RecyclerView.Adapter<ClienteListAdapter.ViewHolder> {

    private ArrayList<Cliente> listaClientes;
    private Context context;

    public ClienteListAdapter(ArrayList<Cliente> listaClientes, Context context) {
        this.listaClientes = listaClientes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_cliente, parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cliente clienteSelecionado = listaClientes.get(position);
        holder.tvNomeCliente.setText(clienteSelecionado.getNome());
        holder.tvCpfCliente.setText(clienteSelecionado.getCpf());
    }

    @Override
    public int getItemCount() {
        return this.listaClientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNomeCliente;
        public TextView tvCpfCliente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvNomeCliente = itemView.findViewById(R.id.tvNomeCliente);
            this.tvCpfCliente = itemView.findViewById(R.id.tvCpfCliente);
        }
    }
}

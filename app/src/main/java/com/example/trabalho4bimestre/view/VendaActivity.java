package com.example.trabalho4bimestre.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.adapter.PagamentoListAdapter;
import com.example.trabalho4bimestre.adapter.VendaListAdapter;
import com.example.trabalho4bimestre.controller.PagamentoController;
import com.example.trabalho4bimestre.controller.ProdutoController;
import com.example.trabalho4bimestre.controller.VendaController;
import com.example.trabalho4bimestre.model.Pagamento;
import com.example.trabalho4bimestre.model.Produto;
import com.example.trabalho4bimestre.model.Venda;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VendaActivity extends AppCompatActivity {


    private Spinner spProduto;
    private EditText edVendedor;
    private Spinner spTipoPagamento;
    private EditText edQuantidade;
    private EditText edValorUnitario;
    private EditText edValorTotal;

    private VendaController controller;
    private ProdutoController produtoController;
    private PagamentoController pagamentoController;

    private View viewAlert;
    private AlertDialog dialog;
    private RecyclerView rvVenda;
    private ArrayList<Produto> listaProdutos;
    private ArrayList<Pagamento> listaTipoPagamento;
    private FloatingActionButton btCadastroVenda;

    private int posicaoSelecionada = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);
        controller = new VendaController(this);
        produtoController = new ProdutoController(this);
        pagamentoController = new PagamentoController(this);
        btCadastroVenda = findViewById(R.id.btCadastroVenda);
        rvVenda = findViewById(R.id.rvVenda);

        btCadastroVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastro();
            }
        });

        atualizarLista();
    }


    private void abrirCadastro() {
        viewAlert = getLayoutInflater().inflate(R.layout.dialog_cadastro_venda, null);
        spProduto = viewAlert.findViewById(R.id.spProduto);
        spTipoPagamento = viewAlert.findViewById(R.id.spTipoPagamento);
        edVendedor = viewAlert.findViewById(R.id.edVendedor);
        edQuantidade = viewAlert.findViewById(R.id.edQuantidade);


        spProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int posicao, long l) {
                if(posicao > 0){
                    posicaoSelecionada = posicao;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        carregaProdutos();



        spTipoPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int posicao, long l) {
                if(posicao > 0){
                    posicaoSelecionada = posicao;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        carregaTipoPagamento();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastro de Venda");
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
        String retorno = controller.salvarVenda(spProduto.getSelectedItem().toString(), edQuantidade.getText().toString(), spTipoPagamento.getSelectedItem().toString(), edVendedor.getText().toString());
        if(retorno != null){
            if(retorno.contains("itens")){
                edQuantidade.setError(retorno);
                edQuantidade.requestFocus();
            }
        }else{
            Toast.makeText(this,
                    "Pedido de Venda salvo com sucesso!",
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();
            atualizarLista();
        }
    }

    private void atualizarLista(){
        ArrayList<Venda> listaVenda = controller.retornarTodasVendas();
        VendaListAdapter adapter = new VendaListAdapter(listaVenda, this);
        rvVenda.setLayoutManager(new LinearLayoutManager(this));
        rvVenda.setAdapter(adapter);
    }


    private void carregaProdutos() {
        listaProdutos = produtoController.retornarTodosProdutos();
        String[]vetProdutos = new String[listaProdutos.size() + 1];
        vetProdutos[0] = "Selecione o Produto";
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            vetProdutos[i+1] = produto.getDescricao();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, vetProdutos);

        spProduto.setAdapter(adapter);

    }


    private void carregaTipoPagamento() {
        listaTipoPagamento = pagamentoController.retornarTodosPagmentos();
        String[]vetPagamento = new String[listaTipoPagamento.size() + 1];
        vetPagamento[0] = "Selecione a forma de Pagamento";
        for (int i = 0; i < listaTipoPagamento.size(); i++) {
            Pagamento pagamento = listaTipoPagamento.get(i);
            vetPagamento[i+1] = pagamento.getTipoPagamento();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, vetPagamento);

        spTipoPagamento.setAdapter(adapter);

    }
}
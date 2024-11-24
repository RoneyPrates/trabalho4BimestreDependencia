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
import com.example.trabalho4bimestre.adapter.ProdutoListAdapter;
import com.example.trabalho4bimestre.controller.ProdutoController;
import com.example.trabalho4bimestre.model.Produto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {

    private EditText edDescricao;
    private EditText edValor;

    private ProdutoController controller;

    private View viewAlert;
    private AlertDialog dialog;
    private RecyclerView rvProdutos;

    private FloatingActionButton btCadastroProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        controller = new ProdutoController(this);
        rvProdutos = findViewById(R.id.rvProdutos);
        btCadastroProduto = findViewById(R.id.btCadastroProduto);
        btCadastroProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastro();
            }
        });
        atualizarLista();
    }



    private void abrirCadastro() {
        viewAlert = getLayoutInflater().inflate(R.layout.dialog_cadastro_produto, null);

        edDescricao = viewAlert.findViewById(R.id.edDescricao);
        edValor = viewAlert.findViewById(R.id.edValor);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastro de Produto");
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
        String retorno = controller.salvarProduto(edDescricao.getText().toString(),
                edValor.getText().toString());
        if(retorno != null){
            if(retorno.contains("descrição")){
                edDescricao.setError(retorno);
                edDescricao.requestFocus();
            }
            if(retorno.contains("valor")){
                edValor.setError(retorno);
                edValor.requestFocus();
            }
        }else{
            Toast.makeText(this,
                    "Produto salvo com sucesso!",
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();
            atualizarLista();
        }
    }

    private void atualizarLista(){
        ArrayList<Produto> listaProdutos = controller.retornarTodosProdutos();
        ProdutoListAdapter adapter = new ProdutoListAdapter(listaProdutos, this);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setAdapter(adapter);
    }
}
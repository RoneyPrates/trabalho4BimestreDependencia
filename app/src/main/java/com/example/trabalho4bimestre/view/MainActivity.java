package com.example.trabalho4bimestre.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.trabalho4bimestre.R;

public class MainActivity extends AppCompatActivity {

    private Button btVenda;
    private Button btProduto;
    private Button btPagamento;
    private Button btCadastroCliente;
    private Button btCadastroEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btVenda = findViewById(R.id.btVenda);
        btProduto = findViewById(R.id.btProduto);
        btPagamento = findViewById(R.id.btPagamento);
        btCadastroCliente = findViewById(R.id.btCadastroCliente);
        btCadastroEndereco = findViewById(R.id.btCadastroEndereco);

        btVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirVenda();
            }
        });

        btProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirProduto();
            }
        });

        btPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPagamento();
            }
        });

        btCadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastroCliente();
            }
        });

        btCadastroEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastroEndereco();
            }
        });
    }

    public void abrirVenda() {
        Intent intent = new Intent(this, VendaActivity.class);
        startActivity(intent);
    }

    public void abrirProduto() {
        Intent intent = new Intent(this, ProdutoActivity.class);
        startActivity(intent);
    }

    public void abrirPagamento() {
        Intent intent = new Intent(this, PagamentoActivity.class);
        startActivity(intent);
    }

    public void abrirCadastroCliente() {
        Intent intent = new Intent(this, CadastroClienteActivity.class);
        startActivity(intent);
    }

    public void abrirCadastroEndereco() {
        Intent intent = new Intent(this, CadastroEnderecoActivity.class);
        startActivity(intent);
    }
}

package com.example.trabalho4bimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalho4bimestre.helper.SQLiteDataHelper;
import com.example.trabalho4bimestre.model.Endereco;

import java.util.ArrayList;

public class EnderecoDao {

    private static EnderecoDao instancia;
    private SQLiteDatabase db;
    private SQLiteDataHelper sqLiteDataHelper;

    // Defina as colunas corretamente
    private static final String TABLE_ENDERECO = "endereco";
    private static final String COLUMN_CODIGO = "IDENDERECO";       // ID do endereço
    private static final String COLUMN_LOGRADOURO = "LOGRADOURO";   // Logradouro
    private static final String COLUMN_NUMERO = "NUMERO";           // Número do endereço
    private static final String COLUMN_BAIRRO = "BAIRRO";           // Bairro
    private static final String COLUMN_CIDADE = "CIDADE";           // Cidade
    private static final String COLUMN_UF = "UF";                   // Unidade Federativa (Estado)

    private EnderecoDao(Context context) {
        sqLiteDataHelper = new SQLiteDataHelper(context);
    }

    public static EnderecoDao getInstancia(Context context) {
        if (instancia == null) {
            instancia = new EnderecoDao(context);
        }
        return instancia;
    }

    // Método para obter a instância do banco de dados
    private SQLiteDatabase getWritableDB() {
        return sqLiteDataHelper.getWritableDatabase();
    }

    private SQLiteDatabase getReadableDB() {
        return sqLiteDataHelper.getReadableDatabase();
    }

    public void insert(Endereco endereco) {
        db = getWritableDB();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGRADOURO, endereco.getLogradouro());
        values.put(COLUMN_NUMERO, endereco.getNumero());
        values.put(COLUMN_BAIRRO, endereco.getBairro());
        values.put(COLUMN_CIDADE, endereco.getCidade());
        values.put(COLUMN_UF, endereco.getUf());

        try {
            db.insert(TABLE_ENDERECO, null, values);
        } catch (SQLException e) {
            e.printStackTrace(); // Log do erro
        } finally {
            db.close();
        }
    }

    public ArrayList<Endereco> getAll() {
        db = getReadableDB();
        ArrayList<Endereco> listaEnderecos = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_ENDERECO, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Garantindo que as colunas existem no Cursor
                    int codigoIndex = cursor.getColumnIndex(COLUMN_CODIGO); // Verifique o nome da coluna
                    int logradouroIndex = cursor.getColumnIndex(COLUMN_LOGRADOURO);
                    int numeroIndex = cursor.getColumnIndex(COLUMN_NUMERO);
                    int bairroIndex = cursor.getColumnIndex(COLUMN_BAIRRO);
                    int cidadeIndex = cursor.getColumnIndex(COLUMN_CIDADE);
                    int ufIndex = cursor.getColumnIndex(COLUMN_UF);

                    // Verifica se os índices são válidos
                    if (codigoIndex != -1 && logradouroIndex != -1 && numeroIndex != -1 &&
                            bairroIndex != -1 && cidadeIndex != -1 && ufIndex != -1) {

                        // Pega os valores do cursor para cada coluna
                        int codigo = cursor.getInt(codigoIndex);
                        String logradouro = cursor.getString(logradouroIndex);
                        String numero = cursor.getString(numeroIndex);
                        String bairro = cursor.getString(bairroIndex);
                        String cidade = cursor.getString(cidadeIndex);
                        String uf = cursor.getString(ufIndex);

                        // Cria um novo objeto Endereco com os dados obtidos
                        Endereco endereco = new Endereco(codigo, logradouro, numero, bairro, cidade, uf);
                        listaEnderecos.add(endereco);
                    }
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log do erro
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaEnderecos;
    }

    // Outros métodos, como update e delete, podem ser ajustados da mesma forma
}

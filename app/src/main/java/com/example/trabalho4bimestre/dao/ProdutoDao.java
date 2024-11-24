package com.example.trabalho4bimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho4bimestre.helper.SQLiteDataHelper;
import com.example.trabalho4bimestre.model.Produto;

import java.util.ArrayList;

public class ProdutoDao implements IGenericDao<Produto>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private String[] coluna = {"IDPRODUTO", "DESCRICAO", "VALOR"};
    private String tabela = "PRODUTO";
    private Context context;
    private static ProdutoDao instancia;

    public static ProdutoDao getInstancia(Context context){
        if (instancia == null){
            return instancia = new ProdutoDao(context);
        }else {
            return instancia;
        }
    }

    public ProdutoDao(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "TRABDB", null, 1);

        database = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Produto obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(coluna[1], obj.getDescricao());
            valores.put(coluna[2], obj.getValor());

            return database.insert(tabela, null, valores);
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Produto.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Produto obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(coluna[1], obj.getDescricao());
            valores.put(coluna[2], obj.getValor());
            String[] identificador = {String.valueOf(obj.getIdProduto())};

            return database.update(tabela, valores, coluna[0] + "= ?", identificador);

        } catch (SQLException ex) {
            Log.e("TRABALHO", "ERRO: Produto.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Produto obj) {
        try {
            String[]identificador = {String.valueOf(obj.getIdProduto())};
            return database.delete(tabela,coluna[0]+"=?",identificador);
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Produto.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Produto> getAll() {
        ArrayList<Produto> lista = new ArrayList<>();
        try{
            Cursor cursor = database.query(tabela, coluna, null, null, null, null,null);
            if (cursor.moveToFirst()){
                do{
                    Produto produto = new Produto();
                    produto.setIdProduto(cursor.getInt(0));
                    produto.setDescricao(cursor.getString(1));
                    produto.setValor(cursor.getDouble(2));

                    lista.add(produto);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Produto.getAll() "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Produto getById(int id) {
        try{
            String[]identficador = {String.valueOf(id)};
            Cursor cursor = database.query(tabela, coluna, coluna[0]+"=?", identficador, null, null, null);

            if (cursor.moveToFirst()){
                Produto produto = new Produto();
                produto.setIdProduto(cursor.getInt(0));
                produto.setDescricao(cursor.getString(1));
                produto.setValor(cursor.getDouble(2));

                return produto;
            }
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Produto.getById() "+ex.getMessage());
        }
        return null;
    }
    public Produto getByDescricao(String descricao) {
        try{
            String[]identficador = {descricao};
            Cursor cursor = database.query(tabela, coluna, coluna[1]+"=?", identficador, null, null, null);

            if (cursor.moveToFirst()){
                Produto produto = new Produto();
                produto.setIdProduto(cursor.getInt(0));
                produto.setDescricao(cursor.getString(1));
                produto.setValor(cursor.getDouble(2));

                return produto;
            }
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Produto.getById() "+ex.getMessage());
        }
        return null;
    }
}


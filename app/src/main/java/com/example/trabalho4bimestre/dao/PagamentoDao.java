package com.example.trabalho4bimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho4bimestre.helper.SQLiteDataHelper;
import com.example.trabalho4bimestre.model.Pagamento;

import java.util.ArrayList;

public class PagamentoDao implements IGenericDao<Pagamento>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private String[] coluna = {"IDPAGAMENTO", "TIPOPAGAMENTO"};
    private String tabela = "PAGAMENTO";
    private Context context;
    private static PagamentoDao instancia;

    public static PagamentoDao getInstancia(Context context){
        if (instancia == null){
            return instancia = new PagamentoDao(context);
        }else {
            return instancia;
        }
    }

    private PagamentoDao(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "TRABDB", null, 1);

        database = openHelper.getWritableDatabase();
    }


    @Override
    public long insert(Pagamento obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(coluna[1], obj.getTipoPagamento());

            return database.insert(tabela, null, valores);
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Pagamento.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Pagamento obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(coluna[1], obj.getTipoPagamento());

            String[] identificador = {String.valueOf(obj.getIdPagamento())};

            return database.update(tabela, valores, coluna[0] + "= ?", identificador);

        } catch (SQLException ex) {
            Log.e("TRABALHO", "ERRO: Pagamento.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Pagamento obj) {
        try {
            String[]identificador = {String.valueOf(obj.getIdPagamento())};
            return database.delete(tabela,coluna[0]+"=?",identificador);
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Pagamento.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Pagamento> lista = new ArrayList<>();
        try{
            Cursor cursor = database.query(tabela, coluna, null, null, null, null,null);
            if (cursor.moveToFirst()){
                do{
                    Pagamento pagamento = new Pagamento();
                    pagamento.setIdPagamento(cursor.getInt(0));
                    pagamento.setTipoPagamento(cursor.getString(1));

                    lista.add(pagamento);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Pagamento.getAll() "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Pagamento getById(int id) {
        try{
            String[]identficador = {String.valueOf(id)};
            Cursor cursor = database.query(tabela, coluna, coluna[0]+"=?", identficador, null, null, null);

            if (cursor.moveToFirst()){
                Pagamento pagamento = new Pagamento();
                pagamento.setIdPagamento(cursor.getInt(0));
                pagamento.setTipoPagamento(cursor.getString(1));

                return pagamento;
            }
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Pagamento.getById() "+ex.getMessage());
        }
        return null;
    }

    public Pagamento getByTipoPagamento(String descricao) {
        try{
            String[]identficador = {descricao};
            Cursor cursor = database.query(tabela, coluna, coluna[1]+"=?", identficador, null, null, null);

            if (cursor.moveToFirst()){
                Pagamento pagamento = new Pagamento();
                pagamento.setIdPagamento(cursor.getInt(0));
                pagamento.setTipoPagamento(cursor.getString(1));

                return pagamento;
            }
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Pagamento.getById() "+ex.getMessage());
        }
        return null;
    }
    
    
}


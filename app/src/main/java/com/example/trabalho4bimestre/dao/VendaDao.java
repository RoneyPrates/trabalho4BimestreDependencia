package com.example.trabalho4bimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho4bimestre.helper.SQLiteDataHelper;
import com.example.trabalho4bimestre.model.Venda;

import java.util.ArrayList;

public class VendaDao implements IGenericDao<Venda> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private String[] coluna = {"IDVENDA", "PRODUTO", "USUARIO", "PAGAMENTO", "QUANTIDADE", "VALORUNITARIO", "VALORTOTAL"};
    private String tabela = "VENDA";
    private Context context;
    private static VendaDao instancia;

    public static VendaDao getInstancia(Context context){
        if (instancia == null){
            return instancia = new VendaDao(context);
        }else {
            return instancia;
        }
    }

    public VendaDao(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "TRABDB", null, 1);

        database = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Venda obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(coluna[1], obj.getProduto());
            valores.put(coluna[2], obj.getVendedor());
            valores.put(coluna[3], obj.getPagamento());
            valores.put(coluna[4], obj.getQuantidade());
            valores.put(coluna[5], obj.getValorUnitario());
            valores.put(coluna[6], obj.getValorTotal());

            return database.insert(tabela, null, valores);
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Venda.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Venda obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(coluna[1], obj.getProduto());
            valores.put(coluna[2], obj.getVendedor());
            valores.put(coluna[3], obj.getPagamento());
            valores.put(coluna[4], obj.getQuantidade());
            valores.put(coluna[5], obj.getValorUnitario());
            valores.put(coluna[6], obj.getValorTotal());
            String[] identificador = {String.valueOf(obj.getIdVenda())};

            return database.update(tabela, valores, coluna[0] + "= ?", identificador);

        } catch (SQLException ex) {
            Log.e("TRABALHO", "ERRO: LoginVenda.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Venda obj) {
        try {
            String[]identificador = {String.valueOf(obj.getIdVenda())};
            return database.delete(tabela,coluna[0]+"=?",identificador);
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Venda.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Venda> getAll() {
        ArrayList<Venda> lista = new ArrayList<>();
        try{
            Cursor cursor = database.query(tabela, coluna, null, null, null, null,null);
            if (cursor.moveToFirst()){
                do{
                    Venda venda = new Venda();
                    venda.setIdVenda(cursor.getInt(0));
                    venda.setProduto(cursor.getString(1));
                    venda.setVendedor(cursor.getString(2));
                    venda.setPagamento(cursor.getString(3));
                    venda.setQuantidade(cursor.getInt(4));
                    venda.setValorUnitario(cursor.getDouble(5));
                    venda.setValorTotal(cursor.getDouble(6));

                    lista.add(venda);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: Venda.getAll() "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Venda getById(int id) {
        try{
            String[]identficador = {String.valueOf(id)};
            Cursor cursor = database.query(tabela, coluna, coluna[0]+"=?", identficador, null, null, null);

            if (cursor.moveToFirst()){
                Venda venda = new Venda();
                venda.setIdVenda(cursor.getInt(0));
                venda.setProduto(cursor.getString(1));
                venda.setVendedor(cursor.getString(2));
                venda.setPagamento(cursor.getString(3));
                venda.setQuantidade(cursor.getInt(4));
                venda.setValorUnitario(cursor.getInt(5));
                venda.setValorTotal(cursor.getInt(6));

                return venda;
            }
        }catch (SQLException ex){
            Log.e("TRABALHO", "ERRO: LoginSenha.getById() "+ex.getMessage());
        }
        return null;
    }

    }



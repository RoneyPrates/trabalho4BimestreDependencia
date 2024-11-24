package com.example.trabalho4bimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalho4bimestre.helper.SQLiteDataHelper;
import com.example.trabalho4bimestre.model.Cliente;

import java.util.ArrayList;

public class ClienteDao {

    private static ClienteDao instancia;
    private SQLiteDatabase db;
    private SQLiteDataHelper sqLiteDataHelper;

    private static final String TABLE_CLIENTE = "cliente";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_CPF = "cpf";

    private ClienteDao(Context context) {
        sqLiteDataHelper = new SQLiteDataHelper(context);
    }

    public static ClienteDao getInstancia(Context context) {
        if (instancia == null) {
            instancia = new ClienteDao(context);
        }
        return instancia;
    }

    private SQLiteDatabase getWritableDB() {
        return sqLiteDataHelper.getWritableDatabase();
    }

    private SQLiteDatabase getReadableDB() {
        return sqLiteDataHelper.getReadableDatabase();
    }

    public void insert(Cliente cliente) {
        db = getWritableDB();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, cliente.getNome());
        values.put(COLUMN_CPF, cliente.getCpf());

        try {
            db.insert(TABLE_CLIENTE, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public ArrayList<Cliente> getAll() {
        db = getReadableDB();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_CLIENTE, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int nomeIndex = cursor.getColumnIndex(COLUMN_NOME);
                    int cpfIndex = cursor.getColumnIndex(COLUMN_CPF);

                    if (nomeIndex != -1 && cpfIndex != -1) {
                        String nome = cursor.getString(nomeIndex);
                        String cpf = cursor.getString(cpfIndex);
                        Cliente cliente = new Cliente(nome, cpf);
                        listaClientes.add(cliente);
                    }
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaClientes;
    }


    public Cliente getByCpf(String cpf) {
        db = getReadableDB();
        Cliente cliente = null;
        Cursor cursor = null;

        try {
            String selection = COLUMN_CPF + " = ?";
            String[] selectionArgs = { cpf };
            cursor = db.query(TABLE_CLIENTE, null, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int nomeIndex = cursor.getColumnIndex(COLUMN_NOME);
                int cpfIndex = cursor.getColumnIndex(COLUMN_CPF);

                if (nomeIndex != -1 && cpfIndex != -1) {
                    String nome = cursor.getString(nomeIndex);
                    String cpfCliente = cursor.getString(cpfIndex);
                    cliente = new Cliente(nome, cpfCliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return cliente;
    }


    public void update(Cliente cliente) {
        db = getWritableDB();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, cliente.getNome());
        values.put(COLUMN_CPF, cliente.getCpf());

        String whereClause = COLUMN_CPF + " = ?";
        String[] whereArgs = { cliente.getCpf() };

        try {
            db.update(TABLE_CLIENTE, values, whereClause, whereArgs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void delete(String cpf) {
        db = getWritableDB();

        String whereClause = COLUMN_CPF + " = ?";
        String[] whereArgs = { cpf };

        try {
            db.delete(TABLE_CLIENTE, whereClause, whereArgs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}

package com.example.trabalho4bimestre.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "trabalho_db";

    public SQLiteDataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (" +
                "IDCLIENTE INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOME VARCHAR(100), " +
                "CPF VARCHAR(14) UNIQUE NOT NULL" +
                ")");

        // Criando a tabela ENDERECO com os novos campos
        sqLiteDatabase.execSQL("CREATE TABLE ENDERECO (" +
                "IDENDERECO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LOGRADOURO VARCHAR(100), " +
                "NUMERO VARCHAR(20), " +
                "BAIRRO VARCHAR(100), " +
                "CIDADE VARCHAR(100), " +
                "UF VARCHAR(2), " +
                "CLIENTE_ID INTEGER, " +
                "FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE(IDCLIENTE)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Alterações para a versão 2 do banco
            sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (" +
                    "IDCLIENTE INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NOME VARCHAR(100), " +
                    "CPF VARCHAR(14) UNIQUE NOT NULL" +
                    ")");

            sqLiteDatabase.execSQL("CREATE TABLE ENDERECO (" +
                    "IDENDERECO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "LOGRADOURO VARCHAR(100), " +
                    "NUMERO VARCHAR(20), " +
                    "BAIRRO VARCHAR(100), " +
                    "CIDADE VARCHAR(100), " +
                    "UF VARCHAR(2), " +
                    "CLIENTE_ID INTEGER, " +
                    "FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE(IDCLIENTE)" +
                    ")");
        }
    }
}

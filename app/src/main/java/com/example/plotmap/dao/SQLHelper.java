package com.example.plotmap.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dbPais";
    private static final int VERSAO_BANCO = 1;
    public static final String TABELA_PAIS = "pais_tabela";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME = "name";
    public static final String COLUNA_CAPITAL = "capital";
    public static final String COLUNA_LATITUDE = "latitude";
    public static final String COLUNA_LONGITUDE = "longitude";
    public static final String COLUNA_REGION = "continente";
    public static final String COLUNA_SUBREGION = "subcontinente";

    public SQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABELA_PAIS + " ( " +
                        COLUNA_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        COLUNA_NOME + " TEXT, " +
                        COLUNA_CAPITAL + " TEXT, " +
                        COLUNA_LATITUDE+ " REAL, " +
                        COLUNA_LONGITUDE+ " REAL, " +
                        COLUNA_REGION + " TEXT, " +
                        COLUNA_SUBREGION + " TEXT)"
        );

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // para as próximas versões
    }

}

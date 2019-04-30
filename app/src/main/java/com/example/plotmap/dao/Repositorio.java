package com.example.plotmap.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.plotmap.model.Pais;

public class Repositorio {

    private SQLHelper helper;
    private SQLiteDatabase db;

    public Repositorio(Context ctx){
        helper = new SQLHelper(ctx);
    }

    public long inserir(Pais pais){
        db = helper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLHelper.COLUNA_NOME, pais.getNome());
        cv.put(SQLHelper.COLUNA_CAPITAL, pais.getCapital());
        try {
            cv.put(SQLHelper.COLUNA_LATITUDE, pais.getLatlong().get(0));
            cv.put(SQLHelper.COLUNA_LONGITUDE, pais.getLatlong().get(1));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }

        cv.put(SQLHelper.COLUNA_REGION, pais.getContinente());
        cv.put(SQLHelper.COLUNA_SUBREGION, pais.getSubcontinente());

        long id = db.insert(SQLHelper.TABELA_PAIS, null, cv);

        if(id != -1){
            pais.id = id;
        }
        db.close();
        return id;
    }

    public void excluirAll(){
        db = helper.getWritableDatabase();
        db.delete(SQLHelper.TABELA_PAIS, null, null);
        db.close();
    }

    public List<Pais> listarPais() {
        String sql = "SELECT * FROM " + SQLHelper.TABELA_PAIS;
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Pais> list = new ArrayList();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(
                    cursor.getColumnIndex(SQLHelper.COLUNA_ID)
            );
            String nome = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_NOME)
            );
            String capital = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_CAPITAL)
            );
            Double latitude = cursor.getDouble(
                    cursor.getColumnIndex(SQLHelper.COLUNA_LATITUDE)
            );
            Double longitude = cursor.getDouble(
                    cursor.getColumnIndex(SQLHelper.COLUNA_LATITUDE)
            );
            String region = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_REGION)
            );
            String subregion = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_SUBREGION)
            );

            List<Double> latlong =  new ArrayList<Double>(2);
            latlong.add(latitude);
            latlong.add(longitude);

            Pais pais = new Pais( nome, capital, region, subregion, latlong);
            list.add(pais);
        }
        cursor.close();
        return list;
    }

}